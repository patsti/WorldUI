var animation = bodymovin.loadAnimation({
    container: document.getElementById('animerror'),
    render: 'svg',
    loop: true,
    autoplay: true,
    path: '/lottie/error.json'
})

var animation = bodymovin.loadAnimation({
    container: document.getElementById('correct_answer'),
    render: 'svg',
    loop: false,
    autoplay: true,
    path: '/lottie/confetti.json'
})

var animation = bodymovin.loadAnimation({
    container: document.getElementById('welcome_animation'),
    render: 'svg',
    loop: false,
    autoplay: true,
    path: '/lottie/welcome.json'
})

