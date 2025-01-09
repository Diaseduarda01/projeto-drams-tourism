//Zerar o Total
var totalAmount = "0,00";

// ===========Remover itens do carrinho===========
// Obter todos os buttons com nome remove-product-button
const removeProductButtons = document.getElementsByClassName("remove-product-button");
//Para cada botão criar o evento para exclusão
//Para cada item ficar ouvindo se houve o click para excluir
for (var i = 0; i < removeProductButtons.length; i++) {
	removeProductButtons[i].addEventListener("click", removeProduct);
}

function removeProduct(event) {
	console.log("Nome do elemento ==> " + event.target.parentElement.parentElement);
	event.target.parentElement.parentElement.remove();
}

//==============Finalizar Compra================
const purchaseButton = document.getElementsByClassName("purchase-button")[0];
purchaseButton.addEventListener("click", makePurchase);
//==============================================

// ==============Adicionar Produto==============
// Obter o click ouvindo cada botão pelo click de cada produto
const addToCartButtons = document.getElementsByClassName("button-hover-background");
for (var i = 0; i < addToCartButtons.length; i++) {
	addToCartButtons[i].addEventListener("click", addProdutoToCard);
}
// =============================================


// ==============Finaliza Compra==============
function makePurchase() {
	if (totalAmount === "0,00") {
		alert("Seu carrinho está vazio!");
	} else {
		alert(`
        Obrigado!!!
        # Compra Finalizada #
        Valor Total Do Pedido:R$ ${totalAmount}`
		);
	}
	document.querySelector(".cart-table tbody").innerHTML = "";
	updateTotal();
}
// =============================================


//Qdo fizer alteração no valor do input e chegar a zero
//excluir o item do carrinho automaticamente
function checkIfInputIsNull(event) {
	if (event.target.value === "0") {
		event.target.parentElement.parentElement.remove();
	}

	updateTotal();
}


// Recebemos o event do button
function addProdutoToCard(event) {
	// Guardar dentro de button o event
	const button = event.target;
	// console.log('Botão Acionado==> ' + button); //==>Item adicionar ao carrinho
	// Retornar ao pai principal para obter os dados do produto
	// const productInfos = button.parentElement.parentElement;
	const detalhe = button.parentElement.parentElement;
	const imagem = productInfos.getElementsByClassName("produto-image")[0].src;
	const nome = productInfos.getElementsByClassName("produto-titulo")[0].innerText;
	const preco = productInfos.getElementsByClassName("produto-preco")[0].innerText;
	// console.log('Item que estamos <=====>' + productTitle);


	// Antes de adicionar um produto ao carrinho temos que ver se
	// Ele já existe no carrinho e add na qtde e somar no total
	const productsCartName = document.getElementsByClassName("cart-product-title");
	// console.log(productsCartName[0].innerText);
	for (var i = 0; i < productsCartName.length; i++) {
		//Caso nome iguais aumento a qtde em 1
		if (productsCartName[i].innerText === productTitle) {
			// Vamos pegar o pai do pai do elemento até obter o item do seu valor
			// Acrescentado +1 com value++
			productsCartName[i].parentElement.parentElement.getElementsByClassName("product-qtd-input")[0].value++;

			//Atualiza Valor
			updateTotal();

			return; //Vai sair dessa função pq se continuar ele vai incluir um produto novo nos itens abaixo
		}
	}

	//Criar um elemento tr
	let newCartProduct = document.createElement("tr");
	newCartProduct.classList.add("cart-product");


	// Foi copiado do html original e alterado com os novos valor clicados
	// Tem que acrescentar dentro do tbody
	newCartProduct.innerHTML = `
      <td class="product-identification">
           <img src="${produto.foto}" alt="${produto.nome}" class="cart-product-image">
           <strong class="cart-product-title">${produto.nome}</strong>
          </td>
          <td>
           <span class="cart-product-price">${produto.preco}</span>
          </td>
          <td>
           <input type="number" value="1" min="0" class="product-qtd-input">
           <button type="button" class="remove-product-button">Remover</button>
          </td>
      `;

	// Para inserir no tbody vamos pegar o caminho
	const tableBody = document.querySelector(".cart-table tbody");
	tableBody.append(newCartProduct);

	updateTotal();

	// Ativado botão de excluir novamente
	newCartProduct.getElementsByClassName("product-qtd-input")[0].addEventListener("change", checkIfInputIsNull);
	newCartProduct.getElementsByClassName("remove-product-button")[0].addEventListener("click", removeProduct);
}


function removeProduct(event) {
	// Para remover o obj tem que subir para seu pai até passando pelo td e chegando ao tr
	event.target.parentElement.parentElement.remove();
	updateTotal();
}


function updateTotal() {
	// Atualizar o Total
	//Obter valor de cada produto
	totalAmount = 0;
	const cartProducts = document.getElementsByClassName("cart-product");
	for (var i = 0; i < cartProducts.length; i++) {
		const productPrice = cartProducts[i]
			.getElementsByClassName("cart-product-price")[0]
			.innerText.replace("R$", "")
			.replace(",", ".");

		const productQuantity =
			cartProducts[i].getElementsByClassName("product-qtd-input")[0].value;

		totalAmount += productPrice * productQuantity;
		console.log(totalAmount.toFixed(2).replace(".", ","));
	}
	totalAmount = totalAmount.toFixed(2).replace(".", ",");
	document.querySelector(".cart-total-container span").innerText =
		"R$ " + totalAmount;
}
