const stripe = Stripe('pk_test_51S1zNd0cWMudol2F2dejG4ZdTWjGf1Fyh4eB0pju4lAXNJZfs3dwfGQK4Yjh2WraqEpHKDYkWsOgLW2IoXBOatpQ001NRPI9BP');
const paymentButton = document.querySelector('#paymentButton');

paymentButton.addEventListener('click', () => {
	stripe.redirectToCheckout({
	sessionId: sessionId
	})
});