const goodAudio = [
    new Audio('static/mp3/announce/good1.mp3'),
    new Audio('static/mp3/announce/good2.mp3'),
    new Audio('static/mp3/announce/good3.mp3'),
    new Audio('static/mp3/announce/good4.mp3'),
]

const goodAudioVolumes = [
    1,
    1,
    1,
    1,
]

const badAudio = [
    new Audio('static/mp3/announce/bad1.mp3'),
    new Audio('static/mp3/announce/bad2.mp3'),
    new Audio('static/mp3/announce/bad3.mp3'),
]

const badAudioVolumes = [
    0.4,
    1,
    0.4,
]

function announceGood() {
    const index = Math.floor(Math.random() * goodAudio.length);
    const audio = goodAudio[index];
    audio.volume = goodAudioVolumes[index];
    audio.play().then(() => {
        audio.currentTime = 0;
    });
    return audio;
}

function announceBad() {
    const index = Math.floor(Math.random() * badAudio.length);
    const audio = badAudio[index];
    audio.volume = badAudioVolumes[index];
    audio.play().then(() => {
        audio.currentTime = 0;
    });
    return audio;
}

function playShootSound() {
    const audio = new Audio('/static/mp3/wot_small.mp3');
    audio.volume = 0.4;
    audio.play().then(r => audio.currentTime = 0);
}
