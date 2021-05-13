import urllib
import requests, bs4
import pandas as pd
from urllib.parse import urlencode, quote_plus, unquote
from fastapi import APIRouter, Depends
from sqlalchemy.orm import Session
from starlette.responses import Response
from starlette.status import HTTP_204_NO_CONTENT
from app.database.conn import db
from app.database.schema import ingredient, ingredient_info, shopping_api
import time
import datetime

router = APIRouter(prefix="/data")


@router.get('/openApi')
def openApi(session: Session = Depends(db.session)):
    """
    `공공데이터 OpenApi`\n
    :param session:
    :return:
    """
    xmlUrl = 'http://apis.data.go.kr/B552895/LocalGovPriceInfoService/getItemPriceResearchSearch'
    apiKey = unquote(
        '1J7amwxnHZ22NEowHAjGL9ihrCj%2FI%2BTvgjIesAJH%2F81p%2FJ0hI2qmYcKjcOrEfPkA0wXoVVgrzJHz4i1%2BRRxv6A%3D%3D')
    date = datetime.date.today()
    ingredientAll = session.query(ingredient).all()
    num = session.query(ingredient_info).filter(ingredient_info.ingredient_info_date == date.strftime("%Y%m%d")).count()
    # 오늘부터 데이터를 안받은 날짜를 탐색
    while num == 0:
        for ingred in ingredientAll:
            """
            openApi url과 함께 보낼 쿼리들을 urllib를 통해 생성
            """
            queryParams = '?' + urlencode(
                {
                    quote_plus('ServiceKey'): apiKey,
                    quote_plus('numOfRows'): '500',
                    quote_plus('pageNo'): '1',
                    quote_plus('examin_de'): date.strftime("%Y%m%d"),
                    quote_plus('prdlst_cd'): ingred.ingredient_name_code
                }
            )
            """
            openApi 호출 후 데이터 받기
            """
            response = requests.get(xmlUrl + queryParams).text.encode('utf-8')
            xmlobj = bs4.BeautifulSoup(response, 'lxml-xml')
            """
            데이터를 받기 위한 딜레이
            """
            time.sleep(0.5)
            """
            openApi 중 item만 받아오기
            """
            rows = xmlobj.findAll('item')
            """
            데이터가 없을 경우 다음 날짜로 이동
            """
            if not rows:
                continue
            rowList = []
            nameList = []
            columnList = []
            rowsLen = len(rows)
            """
            rows의 갯수만큼 반복해서 rowList에 저장
            """
            for i in range(0, rowsLen):
                columns = rows[i].find_all()
                columnsLen = len(columns)
                for j in range(0, columnsLen):
                    """
                    첫번째 columns에는 각 태그명이 들어있음
                    """
                    if i == 0:
                        nameList.append(columns[j].name)

                    eachColumn = columns[j].text
                    columnList.append(eachColumn)
                rowList.append(columnList)
                columnList = []

            """
            받아온 데이터를 데이터프레임으로 변경
            """
            result = pd.DataFrame(rowList, columns=nameList)
            """
            소비자 가격만 가져오고 혹시 도매 가격만 있다면 도매 가격으로 가져옴
            """
            if len(result[result['distb_step'] == '소비자가격'].index) > 0:
                result = result[result['distb_step'] == '소비자가격']
            """
            만약 디테일이 존재한다면 디테일 코드가 일치하는 데이터만 남기기
            """
            if ingred.ingredient_detail_name is not None:
                result = result[result['prdlst_detail_cd'] == ingred.ingredient_detail_name_code]
                if len(result.index) == 0:
                    continue

            """
            데이터를 DB에 저장
            """
            for num, data in result.iterrows():
                item = ingredient_info(ingred.ingredient_id, data['examin_de'], data['examin_amt'],
                                       data['examin_area_nm'], data['stndrd'])
                session.add(item)

        """
        한번에 commit, 만약에 저장할 때 중간에 에러가 발생한다면, 전부 rollback
        """
        date = date + datetime.timedelta(days=-1)
        num = session.query(ingredient_info).filter(
            ingredient_info.ingredient_info_date == date.strftime("%Y%m%d")).count()
        session.commit()
    return Response(status_code=HTTP_204_NO_CONTENT)


@router.get('/naverApi')
def naverApi(session: Session = Depends(db.session)):
    """
    `네이버 Api 연동`\n
    :param session:
    :return:
    """
    client_id = "bYj15csqOZPThddA4ZSW"
    client_secret = "n0KSJICGeo"
    ingredientAll = session.query(ingredient).all()
    encText = ""
    for ingred in ingredientAll:
        if ingred.ingredient_detail_name is not None:
            encText = urllib.parse.quote(ingred.ingredient_detail_name)
        else:
            encText = urllib.parse.quote(ingred.ingredient_name)
        url = "https://openapi.naver.com/v1/search/shop.json?query=" + encText + "&display=10&start=1&sort=sim"
        result = requests.get(url=url, headers={"X-Naver-Client-Id": client_id,
                                                "X-Naver-Client-Secret": client_secret})
        """
        받아온 데이터를 데이터프레임으로 변경
        필요없는 행 제거
        title에 검색한 이름은 태그가 붙어있어서 태그 제거
        """
        time.sleep(1)
        data = pd.DataFrame(result.json()["items"])
        data['title'] = data['title'].str.replace("</b>", "")
        data['title'] = data['title'].str.replace("<b>", "")
        """
        DB에 저장
        """
        for index, prod in data.iterrows():
            item = shopping_api(ingred.ingredient_id, prod['title'], prod['lprice'], prod['mallName'], prod['link'])
            session.add(item)

    session.commit()
    return Response(status_code=HTTP_204_NO_CONTENT)
