function WdateYUIPicker(dateview,format){
    var Event = YAHOO.util.Event,
    Dom = YAHOO.util.Dom,
    dialog,
    calendar;
    if (!dialog) {
        Event.on(document, "click", function(e) {
            var el = Event.getTarget(e);
            var dialogEl = dialog.element;
            if (el != dialogEl && !Dom.isAncestor(dialogEl, el) && el != dateview && !Dom.isAncestor(dateview, el)) {
                dialog.hide();
            }
        });

        function resetHandler() {

            var selDates = calendar.getSelectedDates();
            var resetDate;

            if (selDates.length > 0) {
                resetDate = selDates[0];
            } else {
                resetDate = calendar.today;
            }

            calendar.cfg.setProperty("pagedate", resetDate);
            calendar.render();
        }

        function closeHandler() {
            dialog.hide();
        }

        dialog = new YAHOO.widget.Dialog("container", {
            visible:false,
            context:[dateview.id, "tl", "bl"],
            buttons:[ {
                text:"Reset",
                handler: resetHandler
               // isDefault:true
            }, {
                text:"Close",
                handler: closeHandler
            }],
            draggable:false,
         
            close:true
        });
        dialog.setHeader('');
        dialog.setBody('<div id="cal"></div>');
        dialog.render(document.body);

        dialog.showEvent.subscribe(function() {
            if (YAHOO.env.ua.ie) {
                dialog.fireEvent("changeContent");
            }
        });
    }
    if (!calendar) {
        calendar = new YAHOO.widget.Calendar("cal", {
            iframe:false,
            hide_blank_weeks:true
        });
        calendar.render();

        calendar.selectEvent.subscribe(function() {
            if (calendar.getSelectedDates().length > 0) {
                var selDate = calendar.getSelectedDates()[0];
                dateview.value = formatDate(new Date(selDate.getTime()),format);
            } else {
                dateview.value = "";
            }
            dialog.hide();
        });

        calendar.renderEvent.subscribe(function() {
            dialog.fireEvent("changeContent");
        });
    }
    var seldate = calendar.getSelectedDates();

    if (seldate.length > 0) {
        // Set the pagedate to show the selected date if it exists
        calendar.cfg.setProperty("pagedate", seldate[0]);
        calendar.render();
    }
    dialog.show();

}

      