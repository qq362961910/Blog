/**
 * Created by amen on 17-10-13.
 */
var As = document.getElementById('topnav').getElementsByTagName('a');
var obj = As[0];
for (var i = 1; i < As.length; i++) {
    if (window.location.href.indexOf(As[i].href) >= 0)
        obj = As[i];
}
obj.id = 'topnav_current';
