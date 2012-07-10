/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function __selectAll(selectID){
    var selectInputs = document.getElementsByName(selectID);

    for(var i = 0; i < selectInputs.length; i++){
        selectInputs[i].checked = true;
    }
}