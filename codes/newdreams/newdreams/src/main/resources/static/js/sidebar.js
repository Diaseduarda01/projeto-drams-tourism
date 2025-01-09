// Seleciona a barra de navegação (menu), todos os botões de menu e a sobreposição.
const navBar = document.querySelector("nav"),
	menuBtns = document.querySelectorAll(".menu-icon"),
	overlay = document.querySelector(".overlay");

// Adiciona um ouvinte de evento de clique a cada botão de menu.
menuBtns.forEach((menuBtn) => {
	menuBtn.addEventListener("click", () => {
		// Quando um botão de menu é clicado, a classe "open" na barra de navegação é alternada.
		// Isso abre ou fecha a barra de navegação, dependendo do estado atual.
		navBar.classList.toggle("open");
	});
});

// Adiciona um ouvinte de evento de clique à sobreposição.
overlay.addEventListener("click", () => {
	// Quando a sobreposição é clicada, a classe "open" na barra de navegação é removida.
	// Isso garante que a barra de navegação seja fechada quando a sobreposição é clicada.
	navBar.classList.remove("open");
});
