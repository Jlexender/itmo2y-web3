function loadMainContent() {
    $.ajax({
        url: 'main.xhtml',
        method: 'GET',
        success: function(data) {
            $('#main-content').html(data);
            $.getScript('static/js/main.js');
            $.getScript('static/js/announcer.js');
            $.getScript('static/js/canvas.js');
            $.getScript('static/js/drag.js');
        },
        error: function() {
            $('#main-content').html('<p>Ошибка загрузки содержимого.</p>');
        }
    });
}

function hideMainContent() {
    $('#main-content').hide();
}

function showMainContent() {
    $('#main-content').show();
}

function muteBackgroundAudio() {
    const audio = $('#bg-audio').get(0);
    audio.muted = true;
}

function playBackgroundAudio() {
    const audio = $('#bg-audio').get(0);
    audio.currentTime = 0;
    audio.muted = false;
    audio.volume = 0.4;
    audio.play();
}

function showIndexContent() {
    $('#index-content').show();
}

function hideIndexContent() {
    $('#index-content').hide();
}

