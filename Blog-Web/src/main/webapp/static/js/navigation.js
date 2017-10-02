
//超小屏幕 手机 (<768px)	小屏幕 平板 (≥768px)	中等屏幕 桌面显示器 (≥992px)	大屏幕 大桌面显示器 (≥1200px
function resetFontSize () {
    console.log("width: " + document.body.clientWidth + ", height: " + document.body.clientHeight);
    console.log("font size: " + getComputedStyle(document.getElementsByTagName("body")[0],undefined).fontSize);
}
window.onresize = resetFontSize;
resetFontSize();