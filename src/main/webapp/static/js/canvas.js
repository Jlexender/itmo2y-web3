const canvas = document.getElementById('main-canvas');
const ctx = canvas.getContext('2d');
const radius = 200;
let img = new Image();
img.src = '../static/images/tank.jpg';
const keyPoints = [
    {x: 0, y: 0},
    {x: radius, y: 0},
    {x: radius, y: -radius/2},
    {x: 0, y: -radius/2},
    {x: -radius/2, y: 0},
];
const labels = [
    {x: 0, y: 0, text: '0'},
    {x: 0, y: radius/2, text: 'R/2'},
    {x: 0, y: -radius/2, text: '-R/2'},
    {x: radius, y: 0, text: 'R'},
    {x: -radius/2, y: 0, text: '-R/2'},
];
let points = [];

ctx.lineWidth = 4;

function drawAxis() {
    ctx.beginPath();
    ctx.moveTo(0, canvas.height / 2);
    ctx.lineTo(canvas.width, canvas.height / 2);
    ctx.moveTo(canvas.width / 2, 0);
    ctx.lineTo(canvas.width / 2, canvas.height);
    ctx.stroke();
    drawArrows();
    ctx.beginPath();
    ctx.arc(canvas.width / 2, canvas.height / 2, 4, 0, 2 * Math.PI);
    ctx.fill();
}

function drawArrows() {
    ctx.beginPath();
    ctx.moveTo(canvas.width - 10, canvas.height / 2 - 5);
    ctx.lineTo(canvas.width, canvas.height / 2);
    ctx.lineTo(canvas.width - 10, canvas.height / 2 + 5);
    ctx.moveTo(canvas.width / 2 - 5, 10);
    ctx.lineTo(canvas.width / 2, 0);
    ctx.lineTo(canvas.width / 2 + 5, 10);
    ctx.stroke();
}

function drawBrokenPath() {
    ctx.moveTo(canvas.width / 2 + keyPoints[0].x, canvas.height / 2 - keyPoints[0].y);
    for (let i = 1; i < keyPoints.length; i++) {
        ctx.lineTo(canvas.width / 2 + keyPoints[i].x, canvas.height / 2 - keyPoints[i].y);
    }
}

function drawArc() {
    ctx.arc(canvas.width / 2, canvas.height / 2, radius / 2, Math.PI, 3 * Math.PI / 2);
}

function drawPlot() {
    ctx.fillStyle = 'rgba(255,0,57,0.56)';
    ctx.strokeStyle = 'rgba(255,0,57,0.56)';
    ctx.beginPath();
    drawAxis();
    drawBrokenPath();
    drawArc();
    ctx.fill();
    drawLabels();
    drawPoints();
}

function drawLabels() {
    ctx.font = 'bold 24px serif';
    const oldFillStyle = ctx.fillStyle;
    ctx.fillStyle = 'white';
    labels.forEach(label => {
        ctx.fillText(label.text, canvas.width / 2 + label.x + 5, canvas.height / 2 - label.y - 5);
    });
    ctx.fillStyle = oldFillStyle;
}

function refreshLabels(R) {
    labels[1].text = R / 2;
    labels[2].text = -R / 2;
    labels[3].text = R / 1;
    labels[4].text = -R / 2;
}

function refreshPoints(newR) {
    points.forEach(point => {
        point.x = canvas.width / 2 + point.realX * radius / newR;
        point.y = canvas.height / 2 - point.realY * radius / newR;
    });
}

function drawPoints() {
    points.forEach(point => {
        drawPoint(point);
    });
}

function drawPoint(point) {
    const oldFillStyle = ctx.fillStyle;
    ctx.fillStyle = point.color;
    ctx.beginPath();
    ctx.arc(point.x, point.y, 6, 0, 2 * Math.PI);
    ctx.fill();
    ctx.fillStyle = oldFillStyle;
}

function insertPoint(x, y, r, good) {
    const point = {
        x: canvas.width / 2 + x * radius / r,
        y: canvas.height / 2 - y * radius / r,
        realX: x,
        realY: y,
        color: good ? 'white' : 'red',
    };
    points.push(point);
    drawPoint(point);
}

$(document).ready(() => {
    canvas.width = 500;
    canvas.height = 350;
    img.onload = () => {
        redrawCanvas(getR());
    }
});

function redrawCanvas(newR) {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    refreshLabels(newR);
    refreshPoints(newR);

    const imgWidth = radius * 12 / newR;
    const imgHeight = radius * 12 * (canvas.height / canvas.width) / newR;

    ctx.globalAlpha = 0.8;
    ctx.drawImage(img, (canvas.width - imgWidth) / 2, (canvas.height - imgHeight) / 2, imgWidth, imgHeight);
    ctx.globalAlpha = 1;
    drawPlot();
}

function getR() {
    v = document.getElementById('main-form:r').value;
    console.log('v: ' + v);
    return document.getElementById('main-form:r').value;
}

document.getElementById('main-form:r').addEventListener('input', () => {
    redrawCanvas(getR());
});

$('#main-canvas').click((event) => {
    const rect = canvas.getBoundingClientRect();
    const x = event.clientX - rect.left;
    const y = event.clientY - rect.top;
    const r = getR();
    const realX = ((x - canvas.width / 2) * r / radius).toFixed(2);
    const realY = ((canvas.height / 2 - y) * r / radius).toFixed(2);

    document.getElementById('main-form:x').value = realX;
    document.getElementById('main-form:y').value = realY;

    // submit form
    document.getElementById('main-form:submit-button').click();
});
