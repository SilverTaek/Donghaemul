/* eslint-disable valid-jsdoc */
/* eslint-disable require-jsdoc */
/**
 * @project-site    http://blog.cmiscm.com/?p=3303
 * @repository		https://github.com/cmiscm/cm-rotate.js
 * @author		    Jongmin Kim - cmiscm.com
 * @version 	    1.0
 * @license		    MIT License
 */

const CMRotate =
  CMRotate ||
  (function() {
    const _public = {};
    let $contaier;
    let $cursor;
    const _isTouch = 'ontouchstart' in window;
    let _isTrans3D;
    let _cssTransform;
    const PI = Math.PI / 180;
    let _radius;
    let _gap;
    let _ty;
    let _posTotal;
    let _itemW;
    let _itemH;
    let _itemHW;
    let _itemHH;
    let _itemCur = 0;
    const _posArr = [];
    const _itemArr = [];
    let _bgArr;
    let _bgTotal;
    let _centerX;
    let _centerY;
    let _isDispose = false;
    let _fn;
    let _isDrag = false;
    let _oldMouseX;
    let _moveX = 0;
    let _isCard = false;

    /**
     * init
     *
     * div - DIV element ID
     * tw - Plane Width
     * th - Plane Height
     * ty - Y position distance from bottom
     * gap - Gap between each Plane
     * radius - Circle Radius
     * bg - Background image Array
     * fn - Mouse click function on each Plane
     */
    function init(div, tw, th, ty, gap, radius, bg, fn) {
      $contaier = document.getElementById(div);
      $cursor = document.getElementById('cursor');

      _cssTransform = getCSSTransform();
      if (!_cssTransform) {
        alert('Your browser does not seem to support CSS Transform.');
        return;
      }
      _isTrans3D = has3d();

      _fn = fn;
      _bgArr = bg;
      _bgTotal = _bgArr.length;
      _itemW = tw;
      _itemH = th;
      _ty = ty;
      _itemHW = _itemW >> 1;
      _itemHH = _itemH >> 1;
      _gap = gap;
      _radius = radius;
      _posTotal = Math.ceil(360 / _gap);

      let i;
      let id;
      let pos;
      let reverse = 0;
      const hTotal = _posTotal >> 1;
      for (i = 0; i < _posTotal; i++) {
        pos = (i * _gap - 90 + 360) % 360;
        _posArr[i] = { pos: pos, item: null, id: i };
      }
      for (i = _posTotal; i > hTotal; i--) {
        pos = (i * _gap - 90 + 360) % 360;
        if (pos > 90 && pos < 270) {
          id = _bgTotal - 1 - reverse;
          _posArr[i] = { pos: pos, item: null, id: id };
          reverse++;
        }
      }

      // mouse event
      addMouseEvent();

      // resize
      window.onresize = window.onorientationchange = onResize;
      onResize();

      // render
      requestAnimationFrame(animate);
    }

    /**
     * dispose
     */
    function dispose() {
      _isDispose = true;
      if (_isTouch) {
        document.removeEventListener('touchstart', onTouchStart);
        document.removeEventListener('touchmove', onTouchMove);
        document.removeEventListener('touchend', onTouchEnd);
      } else {
        document.removeEventListener('mousedown', onMouseDown);
        document.removeEventListener('mousemove', onMouseMove);
        document.removeEventListener('mouseup', onMouseUp);
      }
    }

    /**
     * click event on each Plane
     */
    function onClick(event) {
      const no = Number(event.currentTarget.id.substr(10, 3));
      const id = _itemArr[no].id;
      _fn(id);
    }

    function onMouseEnter(event) {
      _isCard = true;

      // 커서 크기 설정
      $cursor.style.transform = 'scale(1)';

      // 커서 색 설정
      $cursor.style.opacity = 100;
      if (event.target.getAttribute('data-category') === '농산') {
        $cursor.style.backgroundColor = '#608F58';
      } else if (event.target.getAttribute('data-category') === '축산') {
        $cursor.style.backgroundColor = '#D73F32';
      } else if (event.target.getAttribute('data-category') === '수산') {
        $cursor.style.backgroundColor = '#4B6EB2';
      }
    }

    function onMouseLeave() {
      _isCard = false;
      $cursor.style.transform = 'scale(0)';
      $cursor.style.opacity = 0;
    }

    /**
     * render
     */
    function animate() {
      if (_isDispose) return;
      requestAnimationFrame(animate);

      _moveX = _moveX * 0.9;

      let i;
      let pos = _posArr[0];
      const sita = pos.pos + _moveX * 0.1;
      for (i = 0; i < _posTotal; i++) {
        pos = _posArr[i];
        render(pos, sita + _gap * i, i);
      }
    }

    function render(pos, sita, no) {
      sita = (sita + 360) % 360;
      pos.pos = sita;

      if (sita > 10 && sita < 170) {
        if (pos.item != null) {
          movePlane(pos.item.plane, -5000, -5000, 0);
          pos.item = null;
        }
      } else {
        if (pos.item == null) {
          let prevV;
          let idV;

          if (sita > 170 && sita < 270) {
            prevV = 1;
            idV = -1;
          } else {
            prevV = -1;
            idV = 1;
          }

          const prev = _posArr[getInsideMax(no + prevV, _posTotal)];
          const id = getInsideMax(prev.id + idV, _bgTotal);

          pos.id = id;
          pos.item = getItem(no, id);
        }

        setPos(pos.item, sita);
      }
    }

    /**
     * position
     */
    function setPos(item, sita) {
      const imgPos = circlePos(sita);
      const value = 270 - sita;
      const abs = Math.abs(value);
      const zindex = (100 - abs) | 0;

      movePlane(item.plane, imgPos.x, imgPos.y, sita + 90);

      item.plane.style.zIndex = zindex;
    }

    function circlePos(sita) {
      const cos = Math.cos(sita * PI);
      const sin = Math.sin(sita * PI);
      const imgX = cos * _radius + _centerX - _itemHW;
      const imgY = sin * _radius + _centerY - _itemHH;
      return { x: imgX, y: imgY };
    }

    function movePlane(plane, tx, ty, rot) {
      if (_isTrans3D)
        plane.style[_cssTransform] =
          'translate3d(' + tx + 'px, ' + ty + 'px, 0px) rotate(' + rot + 'deg)';
      else
        plane.style[_cssTransform] = 'translate(' + tx + 'px, ' + ty + 'px) rotate(' + rot + 'deg)';
    }

    /**
     * resize event
     */
    function onResize() {
      let sw;
      let sh;
      if (document.documentElement) {
        sw = document.documentElement.clientWidth;
        sh = document.documentElement.clientHeight;
      } else if (document.body.clientWidth) {
        sw = document.body.clientWidth;
        sh = document.body.clientHeight;
      } else {
        sw = window.innerWidth;
        sh = window.innerHeight;
      }
      _centerX = sw >> 1;
      _centerY = sh + _ty;
    }

    /**
     * mouse & touch event
     */
    function addMouseEvent() {
      if (_isTouch) {
        document.addEventListener('touchstart', onTouchStart, false);
        document.addEventListener('touchmove', onTouchMove, false);
        document.addEventListener('touchend', onTouchEnd, false);
      } else {
        document.addEventListener('mousedown', onMouseDown, false);
        document.addEventListener('mousemove', onMouseMove, false);
        document.addEventListener('mouseup', onMouseUp, false);
      }
    }

    function onTouchStart(event) {
      const mx = event.touches[0].pageX;
      onDown(mx);
    }
    function onTouchMove(event) {
      event.preventDefault();
      const mx = event.touches[0].pageX;
      onMove(mx);
    }
    function onTouchEnd(event) {
      onUp();
    }

    function onMouseDown(event) {
      const mx = event.pageX;
      onDown(mx);
    }
    function onMouseMove(event) {
      const mx = event.pageX;
      onMove(mx);

      if (_isCard) {
        // 커서 위치 설정
        $cursor.style.setProperty('--mouse-x', event.pageX - 20 + 'px');
        $cursor.style.setProperty('--mouse-y', event.pageY - 20 + 'px');
      }
    }
    function onMouseUp(event) {
      onUp();
    }

    function onDown(mx) {
      _isDrag = true;
      _moveX = 0;
      _oldMouseX = mx;
    }
    function onMove(mx) {
      if (!_isDrag) return;
      _moveX = mx - _oldMouseX;
      _oldMouseX = mx;
    }
    function onUp() {
      _isDrag = false;
    }

    function getInsideMax(no, total) {
      return (no + total * (Math.abs((no / 10) | 0) + 1)) % total;
    }

    /**
     * get item
     */
    function getItem(no, id) {
      // find plane
      let plane;
      let i;
      const total = _itemArr.length;
      for (i = 0; i < total; i++) {
        plane = _itemArr[i];
        if (plane.use == 0) {
          plane.use = 1;
          plane.no = no;
          plane.id = id;
          return plane;
        }
      }

      // make new plane
      // card 배경
      const div = document.createElement('div');
      div.id = 'cm-rotate-' + _itemCur;
      div.style.width = _itemW + 'px';
      div.style.height = _itemH + 'px';
      div.style.position = 'absolute';
      div.className = 'card';

      // 로그인,로그아웃 카드
      if (_bgArr[id].ingredientId < 0) {
        // 로그인 아이콘
        const status = document.createElement('div');
        status.className = 'status';
        status.style.width = '39px';
        status.style.height = '39px';
        status.style.background = 'url(' + _bgArr[id].status + ')';
        div.appendChild(status);
        // 로그인 글씨
        const name = document.createElement('p');
        name.className = 'name';
        name.innerText = _bgArr[id].title;
        div.appendChild(name);
      } else {
        // 상품 이미지
        const ingredientImage = document.createElement('img');
        ingredientImage.className = 'ingredientImage';
        ingredientImage.src = _bgArr[id].imageURL;
        div.appendChild(ingredientImage);

        // cursor event용 div
        const cursorWrapper = document.createElement('div');
        cursorWrapper.className = 'cursorWrapper';
        cursorWrapper.setAttribute('data-category', _bgArr[id].ingredientCategory);
        cursorWrapper.addEventListener('mouseenter', onMouseEnter, false); // cursor  이벤트
        cursorWrapper.addEventListener('mouseleave', onMouseLeave, false);
        // plane contents
        // 상품명
        let ingredientName = _bgArr[id].ingredientName;
        if (_bgArr[id].ingredientDetailName !== null) {
          ingredientName = _bgArr[id].ingredientDetailName + '(' + ingredientName + ')';
        }
        const name = document.createElement('p');
        name.className = 'name';
        name.innerText = ingredientName;
        // 상품명이 5글자 이상일 경우 폰트 조정
        if (ingredientName.length >= 10) {
          name.className = 'longLongName';
        } else if (ingredientName.length >= 5) {
          name.className = 'longName';
        }
        // 가격
        const priceWrapper = document.createElement('div');
        priceWrapper.className = 'priceWrapper';
        priceWrapper.innerText = '현재가격';
        const price = document.createElement('p');
        price.className = 'price';
        price.innerText = _bgArr[id].ingredientAvg.ingredientAvgPrice + '원';
        priceWrapper.appendChild(price);
        // 가격 단위
        const unit = document.createElement('p');
        unit.className = 'unit';
        unit.innerText = '(' + _bgArr[id].ingredientUnit + ')';
        // 번호
        const cardNumber = document.createElement('p');
        cardNumber.className = 'num';
        cardNumber.innerText = id + 1;
        // 즐겨찾기 표시
        if (_bgArr[id].favorite === 1) {
          const bookmark = document.createElement('div');
          bookmark.className = 'bookmark';
          bookmark.style.width = '33px';
          bookmark.style.height = '41px';
          bookmark.style.background = 'url(' + _bgArr[id].bookmark + ')';
          div.appendChild(bookmark);
        }
        // status 표시
        if (_bgArr[id].status !== 0) {
          const status = document.createElement('div');
          status.className = 'status';
          status.style.width = '39px';
          status.style.height = '39px';
          status.style.background = 'url(' + _bgArr[id].status + ')';
          div.appendChild(status);
        }
        // card에 삽입
        div.appendChild(cursorWrapper);
        div.appendChild(name);
        div.appendChild(priceWrapper);
        div.appendChild(unit);
        div.appendChild(cardNumber);
      }

      movePlane(div, -5000, -5000, 0);
      $contaier.appendChild(div);
      plane = { plane: div, use: 1, no: no, id: _bgArr[id].ingredientId };
      _itemArr[_itemCur] = plane;
      _itemCur++;
      div.addEventListener('click', onClick, false); // card click 이벤트
      return plane;
    }

    /**
     * get css transform
     */
    function getCSSTransform() {
      const properties = [
        'transform',
        'WebkitTransform',
        'msTransform',
        'MozTransform',
        'OTransform',
      ];
      let p;
      while ((p = properties.shift())) {
        if (typeof $contaier.style[p] != 'undefined') {
          return p;
        }
      }
      return false;
    }

    /**
     * detect translate3d
     * http://stackoverflow.com/questions/5661671/detecting-transform-translate3d-support
     */
    function has3d() {
      const el = document.createElement('p');
      let has3d;
      const transforms = {
        webkitTransform: '-webkit-transform',
        OTransform: '-o-transform',
        msTransform: '-ms-transform',
        MozTransform: '-moz-transform',
        transform: 'transform',
      };

      // Add it to the body to get the computed style.
      document.body.insertBefore(el, null);

      for (const t in transforms) {
        if (el.style[t] !== undefined) {
          el.style[t] = 'translate3d(1px,1px,1px)';
          has3d = window.getComputedStyle(el).getPropertyValue(transforms[t]);
        }
      }

      document.body.removeChild(el);

      return has3d !== undefined && has3d.length > 0 && has3d !== 'none';
    }

    _public.init = init;
    _public.dispose = dispose;

    return _public;
  })();

if (!window.requestAnimationFrame) {
  window.requestAnimationFrame = (function() {
    return (
      window.requestAnimationFrame ||
      window.webkitRequestAnimationFrame ||
      window.mozRequestAnimationFrame ||
      window.oRequestAnimationFrame ||
      window.msRequestAnimationFrame ||
      function(callback) {
        window.setTimeout(callback, 1000 / 60);
      }
    );
  })();
}

export default CMRotate;
