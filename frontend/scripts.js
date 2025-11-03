function speakText(text) {
    if ('speechSynthesis' in window) {
        const utterance = new SpeechSynthesisUtterance(text);
        utterance.lang = 'pt-BR';
        speechSynthesis.speak(utterance);
    } else {
        alert("Este navegador não suporta a funcionalidade de fala.");
    }
}


document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('amianto-form');

    // Evento do formulário de amianto
    if (form) {
        form.addEventListener('submit', checkAmiantoForm);
    }

    function checkAmiantoForm(e) {
        e.preventDefault();

        const cor = document.getElementById('cor').value;
        const textura = document.getElementById('textura').value;
        const uso = document.getElementById('uso').value;

        const combinacoes = [
            {
                tipo: "Crocidolite (Amianto Azul)",
                cor: "2", textura: "1", uso: "1",
                risco: "Alto",
                recomendacoes: [
                    "Evite manuseio sem proteção.",
                    "Isolar a área caso esteja em deterioração.",
                    "Contato com empresa especializada para descarte seguro."
                ]
            },
            {
                tipo: "Amosita (Amianto Marrom)",
                cor: "6", textura: "2", uso: "2",
                risco: "Alto",
                recomendacoes: [
                    "Evitar corte ou perfuração.",
                    "Não tentar remover sozinho.",
                    "Buscar substituição por materiais não fibrosos."
                ]
            },
            {
                tipo: "Serpentina (Amianto Branco)",
                cor: "3", textura: "3", uso: "3",
                risco: "Médio",
                recomendacoes: [
                    "Evitar atrito ou calor intenso.",
                    "Manter longe de ambientes com ventilação forçada.",
                    "Consultar técnico sobre substituição segura."
                ]
            },
            {
                tipo: "Tremolita",
                cor: "3", textura: "4", uso: "4",
                risco: "Alto",
                recomendacoes: [
                    "Evitar reformas sem inspeção prévia.",
                    "Isolar a área afetada.",
                    "Contato com especialista ambiental."
                ]
            },
            {
                tipo: "Actinolita",
                cor: "7", textura: "5", uso: "5",
                risco: "Alto",
                recomendacoes: [
                    "Evitar qualquer perturbação do material.",
                    "Utilizar EPI adequado para inspeção.",
                    "Procurar descarte conforme normas locais."
                ]
            },
            {
                tipo: "Antofilita",
                cor: "1", textura: "6", uso: "6",
                risco: "Moderado",
                recomendacoes: [
                    "Evitar aquecimento ou abrasão.",
                    "Isolamento preventivo da área.",
                    "Avaliação profissional recomendada."
                ]
            }
        ];

        const resultado = combinacoes.find(item =>
            item.cor === cor &&
            item.textura === textura &&
            item.uso === uso
        );

        const container = document.querySelector('.form-container');

        // Remove resultado anterior
        const previous = document.querySelector('.resultado');
        if (previous) previous.remove();

        const respostaDiv = document.createElement('div');
        respostaDiv.style.marginTop = '30px';
        respostaDiv.style.padding = '20px';
        respostaDiv.style.backgroundColor = '#fff';
        respostaDiv.style.borderRadius = '8px';
        respostaDiv.style.boxShadow = '0 0 10px rgba(0,0,0,0.1)';
        respostaDiv.classList.add('resultado');

        if (resultado) {
            respostaDiv.innerHTML = `
                <h3>🧪 Tipo provável: ${resultado.tipo}</h3>
                <p><strong>Nível de risco:</strong> ${resultado.risco}</p>
                <p><strong>Recomendações:</strong></p>
                <ul>
                    ${resultado.recomendacoes.map(rec => `<li>${rec}</li>`).join('')}
                </ul>
            `;
        } else {
            respostaDiv.innerHTML = `
                <h3>❓ Tipo não identificado</h3>
                <p>Os dados selecionados não correspondem a um tipo conhecido de amianto.</p>
                <p>Recomenda-se avaliação profissional do material.</p>
            `;
        }

        container.appendChild(respostaDiv);
    }

    const submitBtn = document.getElementById('submitBtn');
    const respostas = ["1", "2", "2", "3", "3", "4", "2", "3", "1", "2", "3"];

    if (submitBtn) {
        submitBtn.addEventListener('click', checkQuiz);
    }

    function checkQuiz() {
    const alternativas = [];

    for (let i = 1; i <= 11; i++) {
        const resposta = document.querySelector(`input[name="q${i}"]:checked`)?.value;
        alternativas.push(resposta);
    }

        resultado = 0
        for (let i = 0; i < respostas.length; i++) {
            if (alternativas[i] === respostas[i]) {
                resultado++;
            }
        }

        alert(`Você acertou ${resultado} de ${respostas.length} perguntas.`);
    }





    let listening = false;  // Estado do TTS (Texto para fala)
    let isSpeaking = false;  // Flag para verificar se está falando

    const toggleButton = document.getElementById('toggleButton');

    // Recupera o estado de "listening" do LocalStorage
    if (localStorage.getItem('ttsListening') === 'true') {
        listening = true;
        toggleButton.textContent = '🔊 Parar de ouvir';
        enableTextClicks();  // Ativa a funcionalidade de clicar nos textos
    } else {
        toggleButton.textContent = '🔊 Começar a ouvir';
    }

    // Verifica se o botão está presente
    if (toggleButton) {
        toggleButton.addEventListener('click', toggleListening);
    }

    // Função para ativar ou desativar o "ouvir" ao pressionar o botão
    function toggleListening() {
        listening = !listening;  // Alterna o estado de "listening"

        // Atualiza o texto do botão de acordo com o estado
        if (listening) {
            toggleButton.textContent = '🔊 Parar de ouvir';
            enableTextClicks();  // Ativa a funcionalidade de clicar nos textos
        } else {
            toggleButton.textContent = '🔊 Começar a ouvir';
            disableTextClicks();  // Desativa a funcionalidade de clicar nos textos
        }

        // Armazena o estado de "listening" no LocalStorage
        localStorage.setItem('ttsListening', listening);
    }

    // Função para desativar o clique nos textos
    function disableTextClicks() {
        const textElements = document.querySelectorAll('p, h1, h2, h3, h4, h5, h6, span, li, label, option, select');
        textElements.forEach(element => {
            element.removeEventListener('click', onTextClick);
        });
    }






        // Função para ativar o clique nos links e outros elementos de texto
    function enableTextClicks() {
        const textElements = document.querySelectorAll('p, h1, h2, h3, h4, h5, h6, span, li, label, option, select, a');
        textElements.forEach(element => {
            element.addEventListener('click', onTextClick);
        });
    }

    // Função chamada ao clicar em qualquer texto ou link
    function onTextClick(event) {
        if (listening && !isSpeaking) {  // Só fala se o "ouvir" estiver ativado e não estiver falando
            let text = '';

            // Se for um link <a>, verifica se é interno ou externo
            if (event.target.tagName.toLowerCase() === 'a') {
                const linkUrl = event.target.href;

                // Verifica se o link é interno (do mesmo domínio)
                if (isInternalLink(linkUrl)) {
                    // Se for link interno, impede navegação imediata
                    event.preventDefault();  

                    text = event.target.innerText || event.target.textContent;

                    // Fala o texto do link
                    speakText(text);

                    // Depois de falar o texto, navega após o delay (2 segundos)
                    setTimeout(() => {
                        window.location.href = linkUrl;  // Redireciona o usuário após o TTS terminar
                    }, 2000);  // Delay de 2 segundos (ajuste conforme necessário)
                } 
                // Se for link externo, não faz nada
                else {
                    return; // Não faz nada para links externos
                }
            } 
            // Se for um <select>, fala a opção selecionada
            else if (event.target.tagName.toLowerCase() === 'select') {
                const selectedOption = event.target.selectedOptions[0];
                text = selectedOption ? selectedOption.innerText : '';
                speakText(text);
            } 
            else {
                text = event.target.innerText || event.target.textContent;
                speakText(text);  // Fala o texto
            }
        }
    }

    // Função para verificar se o link é interno (mesmo domínio)
    function isInternalLink(url) {
        const linkHost = new URL(url).hostname;
        const currentHost = window.location.hostname;
        return linkHost === currentHost; // Verifica se o domínio é o mesmo
    }


    // Controle de tamanho de fonte com localStorage
const fontButtons = document.querySelectorAll('.font-btn');
const rootElement = document.documentElement;

// Função para aplicar o tamanho
function applyFontSize(size) {
    switch (size) {
        case 'small':
            rootElement.style.fontSize = '14px';
            break;
        case 'medium':
            rootElement.style.fontSize = '16px';
            break;
        case 'large':
            rootElement.style.fontSize = '18px';
            break;
    }

    // Atualiza o botão ativo
    fontButtons.forEach(btn => {
        btn.classList.toggle('active', btn.dataset.size === size);
    });

    // Salva no localStorage
    localStorage.setItem('fontSizePreference', size);
}

// Ao clicar nos botões
fontButtons.forEach(button => {
    button.addEventListener('click', () => {
        const selectedSize = button.dataset.size;
        applyFontSize(selectedSize);
    });
});

// Ao carregar a página, aplica o tamanho salvo
const savedSize = localStorage.getItem('fontSizePreference') || 'medium';
applyFontSize(savedSize);

});