$(document).ready(function() {
    $('#hide-content').click(function() {
        hideMainContent();
        showIndexContent();
        muteBackgroundAudio();
        toggleMute(null);
    });

    $('#mute-button').click(function() {
        toggleMute(!$('#bg-audio').get(0).muted);
    });
});


function toggleMute(mute) {
    const audio = $('#bg-audio').get(0);
    if (mute !== null) {
        audio.muted = mute;
    }
    $('#mute-button').css('background-color', mute ? '#292929' : '#e53935');
}


