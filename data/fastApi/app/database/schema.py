from sqlalchemy import (
    Column,
    Integer,
    Date,
    VARCHAR,
    CHAR,
    func,
    ForeignKey,
)
from sqlalchemy.orm import relationship
from app.database.conn import Base

class member(Base):
    __tablename__ = "member"
    member_id = Column(Integer, primary_key=True, index=True)
    member_email = Column(VARCHAR(128), nullable=False, unique=True, index=True)
    member_password = Column(VARCHAR(255), nullable=True)
    member_name = Column(VARCHAR(10), nullable=False)
    member_platform_type = Column(VARCHAR(10), nullable=True)

    watch = relationship("watch", back_populates='member')

    def __init__(self, member_email, member_password, member_name, member_platform_type):
        self.member_email = member_email
        self.member_password = member_password
        self.member_name = member_name
        self.member_platform_type = member_platform_type


    def __repr__(self):
        return "<member('%s', '%s', '%s', '%s')>" % (self.member_email, self.member_password, self.member_name, self.member_platform_type)


class ingredient(Base):
    __tablename__ = "ingredient"
    ingredient_id = Column(Integer, primary_key=True, index=True)
    ingredient_name = Column(VARCHAR(20), nullable=True)
    ingredient_name_code = Column(VARCHAR(10), nullable=True)
    ingredient_detail_name = Column(VARCHAR(20), nullable=True)
    ingredient_detail_name_code = Column(VARCHAR(10), nullable=True)
    ingredient_category = Column(VARCHAR(10), nullable=True)
    ingredient_image = Column(VARCHAR(255), nullable=True)
    ingredient_unit = Column(VARCHAR(10), nullable=True)

    ingredient_info = relationship("ingredient_info", back_populates='ingredient')
    ingredient_avg = relationship("ingredient_avg", back_populates='ingredient')
    shopping_api = relationship("shopping_api", back_populates='ingredient')
    watch = relationship("watch", back_populates='ingredient')

    def __init__(self, ingredient_name, ingredient_name_code, ingredient_detail_name, ingredient_detail_name_code, ingredient_category, ingredient_image, ingredient_unit):
        self.ingredient_name = ingredient_name
        self.ingredient_name_code = ingredient_name_code
        self.ingredient_detail_name = ingredient_detail_name
        self.ingredient_detail_name_code = ingredient_detail_name_code
        self.ingredient_category = ingredient_category
        self.ingredient_image = ingredient_image
        self.ingredient_unit = ingredient_unit

    def __repr__(self):
        return "<ingredient('%s', '%s', '%s', '%s', '%s', '%s', '%s')>" % (self.ingredient_name, self.ingredient_name_code, self.ingredient_detail_name, self.ingredient_detail_name_code, self.ingredient_category, self.ingredient_image, self.ingredient_unit)


class ingredient_info(Base):
    __tablename__ = "ingredient_info"
    ingredient_info_id = Column(Integer, primary_key=True, index=True)
    ingredient_id = Column(Integer, ForeignKey("ingredient.ingredient_id"), nullable=False)
    ingredient_info_date = Column(Date, nullable=True)
    ingredient_info_price = Column(Integer, nullable=True)
    ingredient_info_area = Column(VARCHAR(10), nullable=True)
    ingredient_info_unit = Column(VARCHAR(10), nullable=True)

    ingredient = relationship("ingredient", back_populates='ingredient_info')

    def __init__(self, ingredient_id, ingredient_info_date, ingredient_info_price, ingredient_info_area, ingredient_info_unit):
        self.ingredient_id = ingredient_id
        self.ingredient_info_date = ingredient_info_date
        self.ingredient_info_price = ingredient_info_price
        self.ingredient_info_area = ingredient_info_area
        self.ingredient_info_unit = ingredient_info_unit

    def __repr__(self):
        return "<ingredient_info('%s', '%s', '%s', '%s', '%s')>" % (self.ingredient_id, self.ingredient_info_date, self.ingredient_info_price, self.ingredient_info_area, self.ingredient_info_unit)


class ingredient_avg(Base):
    __tablename__ = "ingredient_avg"
    ingredient_avg_id = Column(Integer, primary_key=True, index=True)
    ingredient_id = Column(Integer, ForeignKey("ingredient.ingredient_id"), nullable=False)
    ingredient_avg_date = Column(Date, nullable=True)
    ingredient_avg_previous_price = Column(Integer, nullable=True)
    ingredient_avg_price = Column(Integer, nullable=True)
    ingredient_avg_predict_price = Column(Integer, nullable=True)

    ingredient = relationship("ingredient", back_populates='ingredient_avg')

    def __init__(self, ingredient_id, ingredient_avg_date, ingredient_avg_previous_price, ingredient_avg_price, ingredient_avg_predict_price):
        self.ingredient_id = ingredient_id
        self.ingredient_avg_date = ingredient_avg_date
        self.ingredient_avg_previous_price = ingredient_avg_previous_price
        self.ingredient_avg_price = ingredient_avg_price
        self.ingredient_avg_predict_price = ingredient_avg_predict_price

    def __repr__(self):
        return "<ingredient_avg('%s', '%s', '%s', '%s', '%s')>" % (self.ingredient_id, self.ingredient_avg_date, self.ingredient_avg_previous_price, self.ingredient_avg_price, self.ingredient_avg_predict_price)


class shopping_api(Base):
    __tablename__ = "shopping_api"
    shopping_api_id = Column(Integer, primary_key=True, index=True)
    ingredient_id = Column(Integer, ForeignKey("ingredient.ingredient_id"), nullable=False)
    shopping_api_title = Column(VARCHAR(128), nullable=True)
    shopping_api_price = Column(Integer, nullable=True)
    shopping_api_store = Column(VARCHAR(20), nullable=True)
    shopping_api_date = Column(Date, default=func.utc_timestamp())
    shopping_api_link = Column(VARCHAR(255), nullable=True)

    ingredient = relationship("ingredient", back_populates='shopping_api')

    def __init__(self, ingredient_id, shopping_api_title, shopping_api_price, shopping_api_store, shopping_api_link):
        self.ingredient_id = ingredient_id
        self.shopping_api_title = shopping_api_title
        self.shopping_api_price = shopping_api_price
        self.shopping_api_store = shopping_api_store
        self.shopping_api_link = shopping_api_link

    def __repr__(self):
        return "<shopping_api('%s', '%s', '%s', '%s', '%s')>" % (self.ingredient_id, self.shopping_api_title, self.shopping_api_price, self.shopping_api_store, self.shopping_api_link)

class watch(Base):
    __tablename__ = "watch"
    watch_id = Column(Integer, primary_key=True, index=True)
    member_id = Column(Integer, ForeignKey("member.member_id"), nullable=False)
    ingredient_id = Column(Integer, ForeignKey("ingredient.ingredient_id"), nullable=False)
    watch_date = Column(Date, default=func.utc_timestamp())

    member = relationship("member", back_populates='watch')
    ingredient = relationship("ingredient", back_populates='watch')

    def __init__(self, member_id, ingredient_id, watch_date):
        self.member_id = member_id
        self.ingredient_id = ingredient_id
        self.watch_date = watch_date

    def __repr__(self):
        return  "<watch('%s', '%s', '%s')>" % (self.member_id, self.ingredient_id, self.watch_date)


