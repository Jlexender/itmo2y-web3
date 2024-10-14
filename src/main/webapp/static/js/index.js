$(document).ready(function() {
    let loaded = false;
    $('#load-content').click(function() {
        hideIndexContent();
        if (!loaded) {
            loadMainContent();
            loaded = true;
        } else {
            showMainContent();
        }
        playBackgroundAudio();
    });
});
