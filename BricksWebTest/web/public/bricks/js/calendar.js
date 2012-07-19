var calendar_display_time = true;
var todayStringFormat = '[todayString] [year]\u5e74[monthString][day]\u65e5';//底部配置
var pathToImages = '../public/bricks/images/';//配置图片目录
var speedOfSelectBoxSliding = 100;//年份+-按钮的滚动速度,值越大速度越慢
var intervalSelectBox_minutes = 5;//时间分的精度
var calendar_offsetTop = 0;//控件居顶距离
var calendar_offsetLeft = 0;//控件居左距离
var calendarDiv = false;
var MSIE = false;
var Opera = false;
if(navigator.userAgent.indexOf('MSIE') >= 0 && navigator.userAgent.indexOf('Opera') < 0)
    MSIE=true;
if(navigator.userAgent.indexOf('Opera') >= 0)
    Opera=true;
var monthArray = ['1\u6708','2\u6708','3\u6708','4\u6708','5\u6708','6\u6708','7\u6708','8\u6708','9\u6708','10\u6708','11\u6708','12\u6708'];//月份配置
var monthArrayShort = ['1\u6708','2\u6708','3\u6708','4\u6708','5\u6708','6\u6708','7\u6708','8\u6708','9\u6708','10\u6708','11\u6708','12\u6708'];
var dayArray = ['&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\u4e00','&nbsp;&nbsp;\u4e8c','&nbsp;&nbsp;\u4e09','&nbsp;&nbsp;\u56db','&nbsp;&nbsp;\u4e94','&nbsp;&nbsp;\u516d','&nbsp;&nbsp;\u65e5'];
var weekString = '\u5468';//周
var todayString = '\u4eca\u5929:';//今天
var daysInMonthArray = [31,28,31,30,31,30,31,31,30,31,30,31];
var currentMonth;
var currentYear;
var currentHour;
var currentMinute;
var calendarContentDiv;
var returnDateTo;
var returnFormat;
var activeSelectBoxMonth;
var activeSelectBoxYear;
var activeSelectBoxHour;
var activeSelectBoxMinute;
var iframeObj = false;
var iframeObj2 =false;
function EIS_FIX_EI1(where2fixit){
    if(!iframeObj2)
        return;
    iframeObj2.style.display = 'block';
    iframeObj2.style.height =document.getElementById(where2fixit).offsetHeight+1;
    iframeObj2.style.width=document.getElementById(where2fixit).offsetWidth;
    iframeObj2.style.left=getleftPos(document.getElementById(where2fixit))+1;
    iframeObj2.style.top=getTopPos(document.getElementById(where2fixit))-document.getElementById(where2fixit).offsetHeight;
}
function EIS_Hide_Frame(){
    if(iframeObj2)
        iframeObj2.style.display = 'none';
}
var returnDateToYear;
var returnDateToMonth;
var returnDateToDay;
var returnDateToHour;
var returnDateToMinute;
var inputYear;
var inputMonth;
var inputDay;
var inputHour;
var inputMinute;
var calendarDisplayTime = false;
var selectBoxHighlightColor = '#D60808';//选中项颜色
var selectBoxRolloverBgColor = '#E2EBED';//左边周数背景色
var selectBoxMovementInProgress = false;
var activeSelectBox = false;
function cancelCalendarEvent(){
    return false;
}
function isLeapYear(inputYear){
    if(inputYear%400 == 0 || (inputYear%4 == 0 && inputYear%100 != 0))
        return true;
    return false;
}
activeSelectBoxMonth = false;
var activeSelectBoxDirection = false;
function highlightMonthYear(){
    if(activeSelectBoxMonth)
        activeSelectBoxMonth.className='';
    activeSelectBox = this;
    if(this.className=='monthYearActive'){
        this.className='';
    }
    else{
        this.className = 'monthYearActive';
        activeSelectBoxMonth = this;
    }
    if(this.innerHTML.indexOf('-') >= 0 || this.innerHTML.indexOf('+') >=0 ){
        if(this.className=='monthYearActive')
            selectBoxMovementInProgress = true;
        else
            selectBoxMovementInProgress = false;
        if(this.innerHTML.indexOf('-') >= 0)
            activeSelectBoxDirection = -1;
        else
            activeSelectBoxDirection = 1;
    }
    else{
        selectBoxMovementInProgress = false;
    }
}
function showMonthDropDown(){
    if(document.getElementById('monthDropDown').style.display=='block'){
        document.getElementById('monthDropDown').style.display='none';
        EIS_Hide_Frame();
    }
    else{
        document.getElementById('monthDropDown').style.display='block';
        document.getElementById('yearDropDown').style.display='none';
        document.getElementById('hourDropDown').style.display='none';
        document.getElementById('minuteDropDown').style.display='none';
        if (MSIE){
            EIS_FIX_EI1('monthDropDown');
        }
    }
}
function showYearDropDown(){
    if(document.getElementById('yearDropDown').style.display=='block'){
        document.getElementById('yearDropDown').style.display='none';
        EIS_Hide_Frame();
    }
    else{
        document.getElementById('yearDropDown').style.display='block';
        document.getElementById('monthDropDown').style.display='none';
        document.getElementById('hourDropDown').style.display='none';
        document.getElementById('minuteDropDown').style.display='none';
        if(MSIE){
            EIS_FIX_EI1('yearDropDown');
        }
    }
}
function showHourDropDown(){
    if(document.getElementById('hourDropDown').style.display=='block'){
        document.getElementById('hourDropDown').style.display='none';
        EIS_Hide_Frame();
    }
    else{
        document.getElementById('hourDropDown').style.display='block';
        document.getElementById('monthDropDown').style.display='none';
        document.getElementById('yearDropDown').style.display='none';
        document.getElementById('minuteDropDown').style.display='none';
        if(MSIE){
            EIS_FIX_EI1('hourDropDown');
        }
    }
}
function showMinuteDropDown(){
    if(document.getElementById('minuteDropDown').style.display=='block'){
        document.getElementById('minuteDropDown').style.display='none';
        EIS_Hide_Frame();
    }
    else{
        document.getElementById('minuteDropDown').style.display='block';
        document.getElementById('monthDropDown').style.display='none';
        document.getElementById('yearDropDown').style.display='none';
        document.getElementById('hourDropDown').style.display='none';
        if(MSIE){
            EIS_FIX_EI1('minuteDropDown');
        }
    }
}
function selectMonth(){
    document.getElementById('calendar_month_txt').innerHTML = this.innerHTML
    currentMonth = this.id.replace(/[^\d]/g,'');
    document.getElementById('monthDropDown').style.display='none';
    EIS_Hide_Frame();
    for(var no=0;no<monthArray.length;no++){
        document.getElementById('monthDiv_'+no).style.color='';
    }
    this.style.color = selectBoxHighlightColor;
    activeSelectBoxMonth = this;
    writeCalendarContent();
}
function selectHour(){
    document.getElementById('calendar_hour_txt').innerHTML = this.innerHTML
    currentHour = this.innerHTML.replace(/[^\d]/g,'');
    document.getElementById('hourDropDown').style.display='none';
    EIS_Hide_Frame();
    if(activeSelectBoxHour){
        activeSelectBoxHour.style.color='';
    }
    activeSelectBoxHour=this;
    this.style.color = selectBoxHighlightColor;
}
function selectMinute(){
    document.getElementById('calendar_minute_txt').innerHTML = this.innerHTML
    currentMinute = this.innerHTML.replace(/[^\d]/g,'');
    document.getElementById('minuteDropDown').style.display='none';
    EIS_Hide_Frame();
    if(activeSelectBoxMinute){
        activeSelectBoxMinute.style.color='';
    }
    activeSelectBoxMinute=this;
    this.style.color = selectBoxHighlightColor;
}
function selectYear(){
    document.getElementById('calendar_year_txt').innerHTML = this.innerHTML
    currentYear = this.innerHTML.replace(/[^\d]/g,'');
    document.getElementById('yearDropDown').style.display='none';
    EIS_Hide_Frame();
    if(activeSelectBoxYear){
        activeSelectBoxYear.style.color='';
    }
    activeSelectBoxYear=this;
    this.style.color = selectBoxHighlightColor;
    writeCalendarContent();
}

function switchLeftMoths(){
    switchtMoths("left");
}

function switchRightMoths(){
    switchtMoths("right");
}

function switchtMoths(switchContent){
    if(switchContent.indexOf('left')>=0){
        currentMonth=currentMonth-1;
        if(currentMonth<0){
            currentMonth=11;
            currentYear=currentYear-1;
        }
    }
    else{
        currentMonth=currentMonth+1;
        if(currentMonth>11){
            currentMonth=0;
            currentYear=currentYear/1+1;
        }
    }
    writeCalendarContent();
//pickDate();
}


function switchMonth(){
    if(this.src.indexOf('left')>=0){
        currentMonth=currentMonth-1;
        if(currentMonth<0){
            currentMonth=11;
            currentYear=currentYear-1;
        }
    }
    else{
        currentMonth=currentMonth+1;
        if(currentMonth>11){
            currentMonth=0;
            currentYear=currentYear/1+1;
        }
    }
    writeCalendarContent();
}
function createMonthDiv(){
    var div = document.createElement('DIV');
    div.className='monthYearPicker';
    div.id = 'monthPicker';
    for(var no=0;no<monthArray.length;no++){
        var subDiv = document.createElement('DIV');
        subDiv.innerHTML = monthArray[no];
        subDiv.onmouseover = highlightMonthYear;
        subDiv.onmouseout = highlightMonthYear;
        subDiv.onclick = selectMonth;
        subDiv.id = 'monthDiv_' + no;
        subDiv.style.width = '56px';
        subDiv.onselectstart = cancelCalendarEvent;
        div.appendChild(subDiv);
        if(currentMonth && currentMonth==no){
            subDiv.style.color = selectBoxHighlightColor;
            activeSelectBoxMonth = subDiv;
        }
    }
    return div;
}
function changeSelectBoxYear(e,inputObj){
    if(!inputObj)inputObj =this;
    var yearItems = inputObj.parentNode.getElementsByTagName('DIV');
    if(inputObj.innerHTML.indexOf('-')>=0){
        var startYear = yearItems[1].innerHTML/1 -1;
        if(activeSelectBoxYear){
            activeSelectBoxYear.style.color='';
        }
    }
    else{
        startYear = yearItems[1].innerHTML/1 +1;
        if(activeSelectBoxYear){
            activeSelectBoxYear.style.color='';
        }
    }
    for(var no=1;no<yearItems.length-1;no++){
        yearItems[no].innerHTML = startYear+no-1;
        yearItems[no].id = 'yearDiv' + (startYear/1+no/1-1);
    }
    if(activeSelectBoxYear){
        activeSelectBoxYear.style.color='';
        if(document.getElementById('yearDiv'+currentYear)){
            activeSelectBoxYear = document.getElementById('yearDiv'+currentYear);
            activeSelectBoxYear.style.color=selectBoxHighlightColor;
        }
    }
}
function changeSelectBoxHour(e,inputObj){
    if(!inputObj)inputObj = this;
    var hourItems = inputObj.parentNode.getElementsByTagName('DIV');
    if(inputObj.innerHTML.indexOf('-')>=0){
        var startHour = hourItems[1].innerHTML/1 -1;
        if(startHour<0)startHour=0;
        if(activeSelectBoxHour){
            activeSelectBoxHour.style.color='';
        }
    }
    else{
        startHour = hourItems[1].innerHTML/1 +1;
        if(startHour>14)startHour = 14;
        if(activeSelectBoxHour){
            activeSelectBoxHour.style.color='';
        }
    }
    var prefix = '';
    for(var no=1;no<hourItems.length-1;no++){
        if((startHour/1 + no/1) < 11)
            prefix = '0';
        else
            prefix = '';
        hourItems[no].innerHTML = prefix + (startHour+no-1);
        hourItems[no].id = 'hourDiv' + (startHour/1+no/1-1);
    }
    if(activeSelectBoxHour){
        activeSelectBoxHour.style.color='';
        if(document.getElementById('hourDiv'+currentHour)){
            activeSelectBoxHour = document.getElementById('hourDiv'+currentHour);
            activeSelectBoxHour.style.color=selectBoxHighlightColor;
        }
    }
}
function updateYearDiv(){
    var div = document.getElementById('yearDropDown');
    var yearItems = div.getElementsByTagName('DIV');
    for(var no=1;no<yearItems.length-1;no++){
        yearItems[no].innerHTML = currentYear/1 -6 + no;
        if(currentYear==(currentYear/1 -6 + no)){
            yearItems[no].style.color = selectBoxHighlightColor;
            activeSelectBoxYear = yearItems[no];
        }
        else{
            yearItems[no].style.color = '';
        }
    }
}
function updateMonthDiv(){
    for(no=0;no<12;no++){
        document.getElementById('monthDiv_' + no).style.color = '';
    }
    document.getElementById('monthDiv_' + currentMonth).style.color = selectBoxHighlightColor;
    activeSelectBoxMonth = 	document.getElementById('monthDiv_' + currentMonth);
}
function updateHourDiv(){
    var div = document.getElementById('hourDropDown');
    var hourItems = div.getElementsByTagName('DIV');
    var addHours = 0;
    if((currentHour/1 -6 + 1)<0){
        addHours = 	(currentHour/1 -6 + 1)*-1;
    }
    for(var no=1;no<hourItems.length-1;no++){
        var prefix='';
        if((currentHour/1 -6 + no + addHours) < 10)prefix='0';
        hourItems[no].innerHTML = prefix +  (currentHour/1 -6 + no + addHours);
        if(currentHour==(currentHour/1 -6 + no)){
            hourItems[no].style.color = selectBoxHighlightColor;
            activeSelectBoxHour = hourItems[no];
        }else{
            hourItems[no].style.color = '';
        }
    }
}
function updateMinuteDiv(){
    for(no=0;no<60;no+=intervalSelectBox_minutes){
        var prefix = '';
        if(no<10)
            prefix = '0';
        document.getElementById('minuteDiv_' + prefix + no).style.color = '';
    }
    if(document.getElementById('minuteDiv_' + currentMinute)){
        document.getElementById('minuteDiv_' + currentMinute).style.color = selectBoxHighlightColor;
        activeSelectBoxMinute = document.getElementById('minuteDiv_' + currentMinute);
    }
}
function createYearDiv(){
    if(!document.getElementById('yearDropDown')){
        var div = document.createElement('DIV');
        div.className='monthYearPicker';
    }
    else{
        div = document.getElementById('yearDropDown');
        var subDivs = div.getElementsByTagName('DIV');
        for(var no=0;no<subDivs.length;no++){
            subDivs[no].parentNode.removeChild(subDivs[no]);
        }
    }
    var d = new Date();
    if(currentYear){
        d.setFullYear(currentYear);
    }
    var startYear = d.getFullYear()/1 - 5;
    var subDiv = document.createElement('DIV');
    subDiv.innerHTML = '&nbsp;&nbsp;- ';
    subDiv.onclick = changeSelectBoxYear;
    subDiv.onmouseover = highlightMonthYear;
    subDiv.onmouseout = function(){
        selectBoxMovementInProgress = false;
    };
    subDiv.onselectstart = cancelCalendarEvent;
    div.appendChild(subDiv);
    for(no=startYear;no<(startYear+10);no++){
        subDiv = document.createElement('DIV');
        subDiv.innerHTML = no;
        subDiv.onmouseover = highlightMonthYear;
        subDiv.onmouseout = highlightMonthYear;
        subDiv.onclick = selectYear;
        subDiv.id = 'yearDiv' + no;
        subDiv.onselectstart = cancelCalendarEvent;
        div.appendChild(subDiv);
        if(currentYear && currentYear==no){
            subDiv.style.color = selectBoxHighlightColor;
            activeSelectBoxYear = subDiv;
        }
    }
    subDiv = document.createElement('DIV');
    subDiv.innerHTML = '&nbsp;&nbsp;+ ';
    subDiv.onclick = changeSelectBoxYear;
    subDiv.onmouseover = highlightMonthYear;
    subDiv.onmouseout = function(){
        selectBoxMovementInProgress = false;
    };
    subDiv.onselectstart = cancelCalendarEvent;
    div.appendChild(subDiv);
    return div;
}
function slideCalendarSelectBox(){
    if(selectBoxMovementInProgress){
        if(activeSelectBox.parentNode.id=='hourDropDown'){
            changeSelectBoxHour(false,activeSelectBox);
        }
        if(activeSelectBox.parentNode.id=='yearDropDown'){
            changeSelectBoxYear(false,activeSelectBox);
        }
    }
    setTimeout('slideCalendarSelectBox()',speedOfSelectBoxSliding);
}
function createHourDiv(){
    if(!document.getElementById('hourDropDown')){
        var div = document.createElement('DIV');
        div.className='monthYearPicker';
    }else{
        div = document.getElementById('hourDropDown');
        var subDivs = div.getElementsByTagName('DIV');
        for(var no=0;no<subDivs.length;no++){
            subDivs[no].parentNode.removeChild(subDivs[no]);
        }
    }
    if(!currentHour)currentHour=0;
    var startHour = currentHour/1;
    if(startHour>14)startHour=14;
    var subDiv = document.createElement('DIV');
    subDiv.innerHTML = '&nbsp;&nbsp;- ';
    subDiv.onclick = changeSelectBoxHour;
    subDiv.onmouseover = highlightMonthYear;
    subDiv.onmouseout = function(){
        selectBoxMovementInProgress = false;
    };
    subDiv.onselectstart = cancelCalendarEvent;
    div.appendChild(subDiv);
    for(no=startHour;no<startHour+10;no++){
        var prefix = '';
        if(no/1<10)prefix='0';
        subDiv = document.createElement('DIV');
        subDiv.innerHTML = prefix + no;
        subDiv.onmouseover = highlightMonthYear;
        subDiv.onmouseout = highlightMonthYear;
        subDiv.onclick = selectHour;
        subDiv.id = 'hourDiv' + no;
        subDiv.onselectstart = cancelCalendarEvent;
        div.appendChild(subDiv);
        if(currentYear && currentYear==no){
            subDiv.style.color = selectBoxHighlightColor;
            activeSelectBoxYear = subDiv;
        }
    }
    subDiv = document.createElement('DIV');
    subDiv.innerHTML = '&nbsp;&nbsp;+ ';
    subDiv.onclick = changeSelectBoxHour;
    subDiv.onmouseover = highlightMonthYear;
    subDiv.onmouseout = function(){
        selectBoxMovementInProgress = false;
    };
    subDiv.onselectstart = cancelCalendarEvent;
    div.appendChild(subDiv);
    return div;
}
function createMinuteDiv(){
    if(!document.getElementById('minuteDropDown')){
        var div = document.createElement('DIV');
        div.className='monthYearPicker';
    }else{
        div = document.getElementById('minuteDropDown');
        var subDivs = div.getElementsByTagName('DIV');
        for(var no=0;no<subDivs.length;no++){
            subDivs[no].parentNode.removeChild(subDivs[no]);
        }
    }
    var startMinute = 0;
    var prefix = '';
    for(no=startMinute;no<60;no+=intervalSelectBox_minutes){
        if(no<10)
            prefix='0';
        else
            prefix = '';
        var subDiv = document.createElement('DIV');
        subDiv.innerHTML = prefix + no;
        subDiv.onmouseover = highlightMonthYear;
        subDiv.onmouseout = highlightMonthYear;
        subDiv.onclick = selectMinute;
        subDiv.id = 'minuteDiv_' + prefix +  no;
        subDiv.onselectstart = cancelCalendarEvent;
        div.appendChild(subDiv);
        if(currentYear && currentYear==no){
            subDiv.style.color = selectBoxHighlightColor;
            activeSelectBoxYear = subDiv;
        }
    }
    return div;
}
function highlightSelect(){
    if(this.className=='selectBoxTime'){
        this.className = 'selectBoxTimeOver';
        this.getElementsByTagName('IMG')[0].src = pathToImages + 'down_time_over.gif';
    }else if(this.className=='selectBoxTimeOver'){
        this.className = 'selectBoxTime';
        this.getElementsByTagName('IMG')[0].src = pathToImages + 'down_time.gif';
    }

    if(this.className=='selectBox'){
        this.className = 'selectBoxOver';
        this.getElementsByTagName('IMG')[0].src = pathToImages + 'down_over.gif';
    }else if(this.className=='selectBoxOver'){
        this.className = 'selectBox';
        this.getElementsByTagName('IMG')[0].src = pathToImages + 'down.gif';
    }
}
function highlightArrow(){
    if(this.src.indexOf('over')>=0){
        if(this.src.indexOf('left')>=0)this.src = pathToImages + 'left.gif';
        if(this.src.indexOf('right')>=0)this.src = pathToImages + 'right.gif';
    }else{
        if(this.src.indexOf('left')>=0)this.src = pathToImages + 'left_over.gif';
        if(this.src.indexOf('right')>=0)this.src = pathToImages + 'right_over.gif';
    }
}
function highlightClose(){
    if(this.src.indexOf('over')>=0){
        this.src = pathToImages + 'close.gif';
    }else{
        this.src = pathToImages + 'close_over.gif';
    }
}
function closeCalendar(){
    document.getElementById('yearDropDown').style.display='none';
    document.getElementById('monthDropDown').style.display='none';
    document.getElementById('hourDropDown').style.display='none';
    document.getElementById('minuteDropDown').style.display='none';
    calendarDiv.style.display='none';
    if(iframeObj){
        iframeObj.style.display='none';
        EIS_Hide_Frame();
    }
    if(activeSelectBoxMonth)activeSelectBoxMonth.className='';
    if(activeSelectBoxYear)activeSelectBoxYear.className='';
}
//构造顶部工具条
function writeTopBar(){
    //工具条容器
    var topBar = document.createElement('DIV');
    topBar.className = 'topBar';
    topBar.id = 'topBar';
    calendarDiv.appendChild(topBar);

    //年份选择框
    var yearDiv = document.createElement('DIV');
    yearDiv.onmouseover = highlightSelect;
    yearDiv.onmouseout = highlightSelect;
    yearDiv.onclick = showYearDropDown;
    var span = document.createElement('SPAN');
    span.innerHTML = currentYear;
    span.id = 'calendar_year_txt';
    yearDiv.appendChild(span);
    topBar.appendChild(yearDiv);
    var img = document.createElement('IMG');
    img.src = pathToImages + 'down.gif';
    yearDiv.appendChild(img);
    yearDiv.className = 'selectBox';
    if(Opera){
        yearDiv.style.width = '50px';
        img.style.cssText = 'float:right';
        img.style.position = 'relative';
        img.style.styleFloat = 'right';
    }

    //月份选择框
    var monthDiv = document.createElement('DIV');
    monthDiv.id = 'monthSelect';
    monthDiv.onmouseover = highlightSelect;
    monthDiv.onmouseout = highlightSelect;
    monthDiv.onclick = showMonthDropDown;
    span = document.createElement('SPAN');
    span.innerHTML = monthArray[currentMonth];
    span.id = 'calendar_month_txt';
    monthDiv.appendChild(span);
    img = document.createElement('IMG');
    img.src = pathToImages + 'down.gif';
    img.style.position = 'absolute';
    img.style.right = '0px';
    monthDiv.appendChild(img);
    monthDiv.className = 'selectBox';
    if(Opera){
        img.style.cssText = 'float:right;position:relative';
        img.style.position = 'relative';
        img.style.styleFloat = 'right';
    }
    topBar.appendChild(monthDiv);

    //月份下拉框
    var monthPicker = createMonthDiv();
    monthPicker.style.left = '49px';
    monthPicker.style.top = monthDiv.offsetTop + monthDiv.offsetHeight + 1 + 'px';
    monthPicker.style.width ='60px';
    monthPicker.id = 'monthDropDown';
    calendarDiv.appendChild(monthPicker);

    //月份上(图片)
    var leftDiv = document.createElement('DIV');
    leftDiv.style.marginRight = '1px';

    img = document.createElement('IMG');
    img.src = pathToImages + 'left.gif';
    img.onmouseover = highlightArrow;
    img.onclick = switchMonth;
    img.onmouseout = highlightArrow;
    leftDiv.appendChild(img);
    topBar.appendChild(leftDiv);
    if(Opera)leftDiv.style.width = '16px';

    //月份下(图片)
    var rightDiv = document.createElement('DIV');
    rightDiv.style.marginRight = '1px';

    img = document.createElement('IMG');
    img.src = pathToImages + 'right.gif';
    img.onclick = switchMonth;
    img.onmouseover = highlightArrow;
    img.onmouseout = highlightArrow;
    rightDiv.appendChild(img);
    if(Opera)rightDiv.style.width = '16px';
    topBar.appendChild(rightDiv);

    //年份下拉框
    var yearPicker = createYearDiv();
    yearPicker.style.left = '5px';
    yearPicker.style.top = monthDiv.offsetTop + monthDiv.offsetHeight + 1 + 'px';
    yearPicker.style.width = '35px';
    yearPicker.id = 'yearDropDown';
    calendarDiv.appendChild(yearPicker);
    img = document.createElement('IMG');
    img.src = pathToImages + 'close.gif';
    img.style.styleFloat = 'right';
    img.onmouseover = highlightClose;
    img.onmouseout = highlightClose;
    img.onclick = closeCalendar;
    topBar.appendChild(img);
    if(!document.all){
        img.style.position = 'absolute';
        img.style.right = '2px';
    }

}
function writeCalendarContent(){
    var calendarContentDivExists = true;
    if(!calendarContentDiv){
        calendarContentDiv = document.createElement('DIV');
        calendarDiv.appendChild(calendarContentDiv);
        calendarContentDivExists = false;
    }
    currentMonth = currentMonth/1;
    var d = new Date();
    d.setFullYear(currentYear);
    d.setDate(1);
    d.setMonth(currentMonth);
    var dayStartOfMonth = d.getDay();
    if(dayStartOfMonth==0)dayStartOfMonth=7;
    dayStartOfMonth--;
    document.getElementById('calendar_year_txt').innerHTML = currentYear;
    document.getElementById('calendar_month_txt').innerHTML = monthArray[currentMonth];
    document.getElementById('calendar_hour_txt').innerHTML = currentHour;
    document.getElementById('calendar_minute_txt').innerHTML = currentMinute;
    var existingTable = calendarContentDiv.getElementsByTagName('TABLE');
    if(existingTable.length>0){
        calendarContentDiv.removeChild(existingTable[0]);
    }
    var calTable = document.createElement('TABLE');
    calTable.cellSpacing = '0';
    calendarContentDiv.appendChild(calTable);
    var calTBody = document.createElement('TBODY');
    calTable.appendChild(calTBody);
    var row = calTBody.insertRow(-1);
    var cell = row.insertCell(-1);
    cell.innerHTML = weekString;
    cell.style.backgroundColor = selectBoxRolloverBgColor;
    for(var no=0;no<dayArray.length;no++){
        cell = row.insertCell(-1);
        cell.innerHTML = dayArray[no];
    }
    row = calTBody.insertRow(-1);
    cell = row.insertCell(-1);
    cell.style.backgroundColor = selectBoxRolloverBgColor;
    var week = getWeek(currentYear,currentMonth,1);
    cell.innerHTML = week;		// Week
    // last months days
    var lastMonth = currentMonth - 1;
    var lastYear = currentYear;
    if(lastMonth < 0){
        lastYear = currentYear - 1;
        lastMonth = 11;
    }
    var daysInLastMonth = daysInMonthArray[lastMonth];
    if(daysInLastMonth==28){
        if(isLeapYear(lastYear))daysInLastMonth=29;
    }
    for(no=0;no<dayStartOfMonth;no++){
        cell = row.insertCell(-1);
        cell.innerHTML = daysInLastMonth - dayStartOfMonth + no + 1;
        cell.className='inactiveDay';
        cell.onclick = switchLeftMoths;
    }
    var colCounter = dayStartOfMonth;
    var daysInMonth = daysInMonthArray[currentMonth];
    if(daysInMonth==28){
        if(isLeapYear(currentYear))daysInMonth=29;
    }
    for(no=1;no<=daysInMonth;no++){
        d.setDate(no);
        if(colCounter>0 && colCounter%7==0){
            row = calTBody.insertRow(-1);
            cell = row.insertCell(-1);
            week = getWeek(currentYear,currentMonth,no);
            cell.innerHTML = week;		// Week
            cell.style.backgroundColor = selectBoxRolloverBgColor;
        }
        cell = row.insertCell(-1);
        if(currentYear==inputYear && currentMonth == inputMonth && no==inputDay){
            cell.className='activeDay';
        }
        cell.innerHTML = no;
        cell.onclick = pickDate;
        colCounter++;
    }
    // next months days
    var dayEndOfMonth = d.getDay();
    //if(dayEndOfMonth==0)dayEndOfMonth=7;
    dayEndOfMonth = 7 - dayEndOfMonth;
    for(no=0;no<dayEndOfMonth;no++){
        cell = row.insertCell(-1);
        cell.innerHTML = no + 1;
        cell.className='inactiveDay';
        cell.onclick = switchRightMoths;
    }

    if(!document.all){
        if(calendarContentDiv.offsetHeight)
            document.getElementById('topBar').style.top = calendarContentDiv.offsetHeight + document.getElementById('timeBar').offsetHeight + document.getElementById('topBar').offsetHeight -1 + 'px';
        else{
            document.getElementById('topBar').style.top = '';
            document.getElementById('topBar').style.bottom = '0px';
        }
    }
    if(iframeObj){
        if(!calendarContentDivExists)setTimeout('resizeIframe()',350);else setTimeout('resizeIframe()',10);
    }
}
function resizeIframe(){
    iframeObj.style.width = calendarDiv.offsetWidth + 'px';
    iframeObj.style.height = calendarDiv.offsetHeight + 'px' ;
}
function pickTodaysDate(){
    var d = new Date();
    currentMonth = d.getMonth();
    currentYear = d.getFullYear();
    pickDate(false,d.getDate());
}
function pickDate(e,inputDay){
    var month = currentMonth/1 +1;
    if(month<10)month = '0' + month;
    var day;
    if(!inputDay && this)day = this.innerHTML; else day = inputDay;
    if(day/1<10)day = '0' + day;
    if(returnFormat){
        returnFormat = returnFormat.replace('dd',day);
        returnFormat = returnFormat.replace('mm',month);
        returnFormat = returnFormat.replace('yyyy',currentYear);
        returnFormat = returnFormat.replace('hh',currentHour);
        returnFormat = returnFormat.replace('ii',currentMinute);
        returnFormat = returnFormat.replace('d',day/1);
        returnFormat = returnFormat.replace('m',month/1);
        returnDateTo.value = returnFormat;
    }else{
        for(var no=0;no<returnDateToYear.options.length;no++){
            if(returnDateToYear.options[no].value==currentYear){
                returnDateToYear.selectedIndex=no;
                break;
            }
        }
        for(no=0;no<returnDateToMonth.options.length;no++){
            if(returnDateToMonth.options[no].value==month){
                returnDateToMonth.selectedIndex=no;
                break;
            }
        }
        for(no=0;no<returnDateToDay.options.length;no++){
            if(returnDateToDay.options[no].value==day){
                returnDateToDay.selectedIndex=no;
                break;
            }
        }
        if(calendarDisplayTime){
            for(no=0;no<returnDateToHour.options.length;no++){
                if(returnDateToHour.options[no].value==currentHour){
                    returnDateToHour.selectedIndex=no;
                    break;
                }
            }
            for(no=0;no<returnDateToMinute.options.length;no++){
                if(returnDateToMinute.options[no].value==currentMinute){
                    returnDateToMinute.selectedIndex=no;
                    break;
                }
            }
        }
    }
    closeCalendar();
}
function getWeek(year,month,day){
    day = day/1;
    year = year /1;
    month = month/1 + 1; //use 1-12
    var a = Math.floor((14-(month))/12);
    var y = year+4800-a;
    var m = (month)+(12*a)-3;
    var jd = day + Math.floor(((153*m)+2)/5) +
    (365*y) + Math.floor(y/4) - Math.floor(y/100) +
    Math.floor(y/400) - 32045;      // (gregorian calendar)
    var d4 = (jd+31741-(jd%7))%146097%36524%1461;
    var L = Math.floor(d4/1460);
    var d1 = ((d4-L)%365)+L;
    NumberOfWeek = Math.floor(d1/7) + 1;
    return NumberOfWeek;
}
function writeTimeBar(){
    var timeBar = document.createElement('DIV');
    timeBar.id = 'timeBar';
    timeBar.className = 'timeBar';
    var subDiv = document.createElement('DIV');
    subDiv.innerHTML = 'Time:';
    var hourDiv = document.createElement('DIV');
    hourDiv.onmouseover = highlightSelect;
    hourDiv.onmouseout = highlightSelect;
    hourDiv.onclick = showHourDropDown;
    hourDiv.style.width = '30px';
    var span = document.createElement('SPAN');
    span.innerHTML = currentHour;
    span.id = 'calendar_hour_txt';
    hourDiv.appendChild(span);
    timeBar.appendChild(hourDiv);
    var img = document.createElement('IMG');
    img.src = pathToImages + 'down_time.gif';
    hourDiv.appendChild(img);
    hourDiv.className = 'selectBoxTime';
    if(Opera){
        hourDiv.style.width = '30px';
        img.style.cssText = 'float:right';
        img.style.position = 'relative';
        img.style.styleFloat = 'right';
    }
    var hourPicker = createHourDiv();
    hourPicker.style.left = '130px';
    hourPicker.style.width = '35px';
    hourPicker.id = 'hourDropDown';
    calendarDiv.appendChild(hourPicker);
    var minuteDiv = document.createElement('DIV');
    minuteDiv.onmouseover = highlightSelect;
    minuteDiv.onmouseout = highlightSelect;
    minuteDiv.onclick = showMinuteDropDown;
    minuteDiv.style.width = '30px';
    span = document.createElement('SPAN');
    span.innerHTML = currentMinute;
    span.id = 'calendar_minute_txt';
    minuteDiv.appendChild(span);
    timeBar.appendChild(minuteDiv);
    img = document.createElement('IMG');
    img.src = pathToImages + 'down_time.gif';
    minuteDiv.appendChild(img);
    minuteDiv.className = 'selectBoxTime';
    if(Opera){
        minuteDiv.style.width = '30px';
        img.style.cssText = 'float:right';
        img.style.position = 'relative';
        img.style.styleFloat = 'right';
    }
    var minutePicker = createMinuteDiv();
    minutePicker.style.left = '167px';
    minutePicker.style.width = '35px';
    minutePicker.id = 'minuteDropDown';
    calendarDiv.appendChild(minutePicker);
    return timeBar;
}
function writeBottomBar(){
    var d = new Date();
    var bottomBar = document.createElement('DIV');
    bottomBar.id = 'bottomBar';
    bottomBar.style.cursor = 'pointer';
    bottomBar.className = 'todaysDate';
    var subDiv = document.createElement('DIV');
    subDiv.onclick = pickTodaysDate;
    subDiv.id = 'todaysDateString';
    subDiv.style.width = (calendarDiv.offsetWidth - 95) + 'px';
    var day = d.getDay();
    if(day==0)day = 7;
    day--;
    var bottomString = todayStringFormat;
    bottomString = bottomString.replace('[monthString]',monthArrayShort[d.getMonth()]);
    bottomString = bottomString.replace('[day]',d.getDate());
    bottomString = bottomString.replace('[year]',d.getFullYear());
    bottomString = bottomString.replace('[dayString]',dayArray[day].toLowerCase());
    bottomString = bottomString.replace('[UCFdayString]',dayArray[day]);
    bottomString = bottomString.replace('[todayString]',todayString);
    subDiv.innerHTML = todayString + ': ' + d.getDate() + '. ' + monthArrayShort[d.getMonth()] + ', ' +  d.getFullYear() ;
    subDiv.innerHTML = bottomString ;
    bottomBar.appendChild(subDiv);
    var timeDiv = writeTimeBar();
    bottomBar.appendChild(timeDiv);
    calendarDiv.appendChild(bottomBar);
}
function getTopPos(inputObj){
    var returnValue = inputObj.offsetTop + inputObj.offsetHeight;
    while((inputObj = inputObj.offsetParent) != null)returnValue += inputObj.offsetTop;
    return returnValue + calendar_offsetTop;
}
function getleftPos(inputObj){
    var returnValue = inputObj.offsetLeft;
    while((inputObj = inputObj.offsetParent) != null)returnValue += inputObj.offsetLeft;
    return returnValue + calendar_offsetLeft;
}
function positionCalendar(inputObj){
    calendarDiv.style.left = getleftPos(inputObj) + 'px';
    calendarDiv.style.top = getTopPos(inputObj) + 'px';
    if(iframeObj){
        iframeObj.style.left = calendarDiv.style.left;
        iframeObj.style.top =  calendarDiv.style.top;
        iframeObj2.style.left = calendarDiv.style.left;
        iframeObj2.style.top =  calendarDiv.style.top;
    }
}
function initCalendar(){
    if(MSIE){
        iframeObj = document.createElement('IFRAME');
        iframeObj.style.position = 'absolute';
        iframeObj.border='0px';
        iframeObj.style.border = '0px';
        iframeObj.style.backgroundColor = '#FF0000';
        iframeObj2 = document.createElement('IFRAME');
        iframeObj2.style.position = 'absolute';
        iframeObj2.border='0px';
        iframeObj2.style.border = '0px';
        iframeObj2.style.height = '1px';
        iframeObj2.style.width = '1px';
        document.body.appendChild(iframeObj2);
        iframeObj2.src = 'blank.html';
        iframeObj.src = 'blank.html';
        document.body.appendChild(iframeObj);
    }
    calendarDiv = document.createElement('DIV');
    calendarDiv.id = 'calendarDiv';
    calendarDiv.style.zIndex = 1000;
    slideCalendarSelectBox();
    document.body.appendChild(calendarDiv);
    writeBottomBar();
    writeTopBar();
    if(!currentYear){
        var d = new Date();
        currentMonth = d.getMonth();
        currentYear = d.getFullYear();
    }
    writeCalendarContent();
}
function setTimeProperties(){
    if(!calendarDisplayTime){
        document.getElementById('timeBar').style.display='none';
        document.getElementById('timeBar').style.visibility='hidden';
        document.getElementById('todaysDateString').style.width = '100%';
    }else{
        document.getElementById('timeBar').style.display='block';
        document.getElementById('timeBar').style.visibility='visible';
        document.getElementById('hourDropDown').style.top = document.getElementById('calendar_minute_txt').parentNode.offsetHeight + calendarContentDiv.offsetHeight + document.getElementById('topBar').offsetHeight + 'px';
        document.getElementById('minuteDropDown').style.top = document.getElementById('calendar_minute_txt').parentNode.offsetHeight + calendarContentDiv.offsetHeight + document.getElementById('topBar').offsetHeight + 'px';
        document.getElementById('minuteDropDown').style.right = '50px';
        document.getElementById('hourDropDown').style.right = '50px';
        document.getElementById('todaysDateString').style.width = '115px';
    }
}
function calendarSortItems(a,b){
    return a/1 - b/1;
}
//吴剑 2010-6-13
function isDateTime(str){
    var reg = /^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))( (([0-9])|([0-1][0-9])|(2[0-3])))?(:(([0-9])|([0-5][0-9])))?(:(([0-9])|([0-5][0-9])))?$/;
    if (reg.test(str)){
        return true;
    }
    else{
        return false;
    }
}
/*************************************************************
//主调用函数
//
//参数说明
//inputField	: 承载输出值的input控件
//format		: 格式化字符串，如: "yyyy-mm-dd hh:ii"
//buttonObj		: 触发对话框的控件(依据此控件进行相对定位显示)
//displayTime	: 是否使用时间(小时、分),值为true或false,该参数可略
//initVal		: 初始值(yyyy-mm-dd hh:ii),该参数可略
*************************************************************/
function displayCalendar(inputField,format,buttonObj,displayTime,initVal){
    if(displayTime){
        calendarDisplayTime=true;
    }else{
        calendarDisplayTime = false;
    }

    //设定初始值且输入框为空时初始值生效
    if(initVal && initVal.length > 0 && inputField.value.length == 0){
        if(isDateTime(initVal)){
            var _datetime = initVal.replace(new RegExp("[^0-9]", "g"), ",").split(",");
            //年、月、日
            currentYear = _datetime[0];
            currentMonth = _datetime[1] - 1;
            tmpDay = _datetime[2];
            currentHour = '00';
            currentMinute = '00';

            if(_datetime.length == 4){
                currentHour = _datetime[3];
            }
            else if(_datetime.length == 5){
                currentHour = _datetime[3];
                currentMinute = _datetime[4];
            }
        }
    }
    else{
        if(inputField.value.length > 0){
            if(!format.match(/^[0-9]*?$/gi)){
                var items = inputField.value.split(/[^0-9]/gi);
                var positionArray = new Array();
                positionArray['m'] = format.indexOf('mm');
                if(positionArray['m']==-1)positionArray['m'] = format.indexOf('m');
                positionArray['d'] = format.indexOf('dd');
                if(positionArray['d']==-1)positionArray['d'] = format.indexOf('d');
                positionArray['y'] = format.indexOf('yyyy');
                positionArray['h'] = format.indexOf('hh');
                positionArray['i'] = format.indexOf('ii');

                var positionArrayNumeric = Array();
                positionArrayNumeric[0] = positionArray['m'];
                positionArrayNumeric[1] = positionArray['d'];
                positionArrayNumeric[2] = positionArray['y'];
                positionArrayNumeric[3] = positionArray['h'];
                positionArrayNumeric[4] = positionArray['i'];
                positionArrayNumeric = positionArrayNumeric.sort(calendarSortItems);
                var itemIndex = -1;
                currentHour = '00';
                currentMinute = '00';
                for(var no=0;no<positionArrayNumeric.length;no++){
                    if(positionArrayNumeric[no]==-1)
                        continue;
                    itemIndex++;
                    if(positionArrayNumeric[no]==positionArray['m']){
                        currentMonth = items[itemIndex]-1;
                        continue;
                    }
                    if(positionArrayNumeric[no]==positionArray['y']){
                        currentYear = items[itemIndex];
                        continue;
                    }
                    if(positionArrayNumeric[no]==positionArray['d']){
                        tmpDay = items[itemIndex];
                        continue;
                    }
                    if(positionArrayNumeric[no]==positionArray['h']){
                        currentHour = items[itemIndex];
                        continue;
                    }
                    if(positionArrayNumeric[no]==positionArray['i']){
                        currentMinute = items[itemIndex];
                        continue;
                    }
                }
                currentMonth = currentMonth / 1;
                tmpDay = tmpDay / 1;
            }
            else{
                var monthPos = format.indexOf('mm');
                currentMonth = inputField.value.substr(monthPos,2)/1 -1;
                var yearPos = format.indexOf('yyyy');
                currentYear = inputField.value.substr(yearPos,4);
                var dayPos = format.indexOf('dd');
                tmpDay = inputField.value.substr(dayPos,2);

                var hourPos = format.indexOf('hh');
                if(hourPos>=0){
                    tmpHour = inputField.value.substr(hourPos,2);
                    currentHour = tmpHour;
                }
                else{
                    currentHour = '00';
                }
                var minutePos = format.indexOf('ii');
                if(minutePos>=0){
                    tmpMinute = inputField.value.substr(minutePos,2);
                    currentMinute = tmpMinute;
                }
                else{
                    currentMinute = '00';
                }
            }
        }
        else{
            var d = new Date();
            currentMonth = d.getMonth();
            currentYear = d.getFullYear();
            currentHour = '08';
            currentMinute = '00';
            tmpDay = d.getDate();
        }
    }

    inputYear = currentYear;
    inputMonth = currentMonth;
    inputDay = tmpDay/1;
    if(!calendarDiv){
        initCalendar();
    }
    else{
        if(calendarDiv.style.display=='block'){
            closeCalendar();
            return false;
        }
        writeCalendarContent();
    }
    returnFormat = format;
    returnDateTo = inputField;
    positionCalendar(buttonObj);
    calendarDiv.style.visibility = 'visible';
    calendarDiv.style.display = 'block';
    if(iframeObj){
        iframeObj.style.display = '';
        iframeObj.style.height = '140px';
        iframeObj.style.width = '195px';
        iframeObj2.style.display = '';
        iframeObj2.style.height = '140px';
        iframeObj2.style.width = '195px';
    }

    setTimeProperties();
    updateYearDiv();
    updateMonthDiv();
    updateMinuteDiv();
    updateHourDiv();
//    return true;
}
function displayCalendarSelectBox(yearInput,monthInput,dayInput,hourInput,minuteInput,buttonObj){
    if(!hourInput)calendarDisplayTime=false; else calendarDisplayTime = true;
    currentMonth = monthInput.options[monthInput.selectedIndex].value/1-1;
    currentYear = yearInput.options[yearInput.selectedIndex].value;
    if(hourInput){
        currentHour = hourInput.options[hourInput.selectedIndex].value;
        inputHour = currentHour/1;
    }
    if(minuteInput){
        currentMinute = minuteInput.options[minuteInput.selectedIndex].value;
        inputMinute = currentMinute/1;
    }
    inputYear = yearInput.options[yearInput.selectedIndex].value;
    inputMonth = monthInput.options[monthInput.selectedIndex].value/1 - 1;
    inputDay = dayInput.options[dayInput.selectedIndex].value/1;
    if(!calendarDiv){
        initCalendar();
    }else{
        writeCalendarContent();
    }
    returnDateToYear = yearInput;
    returnDateToMonth = monthInput;
    returnDateToDay = dayInput;
    returnDateToHour = hourInput;
    returnDateToMinute = minuteInput;
    returnFormat = false;
    returnDateTo = false;
    positionCalendar(buttonObj);
    calendarDiv.style.visibility = 'visible';
    calendarDiv.style.display = 'block';
    if(iframeObj){
        iframeObj.style.display = '';
        iframeObj.style.height = calendarDiv.offsetHeight + 'px';
        iframeObj.style.width = calendarDiv.offsetWidth + 'px';
        iframeObj2.style.display = '';
        iframeObj2.style.height = calendarDiv.offsetHeight + 'px';
        iframeObj2.style.width = calendarDiv.offsetWidth + 'px'
    }
    setTimeProperties();
    updateYearDiv();
    updateMonthDiv();
    updateHourDiv();
    updateMinuteDiv();
}
