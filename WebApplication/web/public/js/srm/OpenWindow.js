/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//PanelInit(链接地址,打开方式,唯一标识,form DOM)
function OpenWindow(url,windowname) {
    window.open(url,windowname,'menubar=no,location=no,top=0,left=0,width=950,height=650,status=yes,scrollbars=yes,resizable=yes');
    return;
}
function OpenTishiWindow(url,windowname){
    window.open( url , windowname,'location=no,menubar=no,left=' +((window.screen.width-350)/2) + ',top=' +((window.screen.height -260)/2) +',width=350,height=260,resizable=yes,scrollbars=no,status=no');
    return;
}
