function login(email, senha){
    $.ajax({
        url: '/calculadora/login',
        method: 'POST',
        data:{email:email, senha:senha},
        statusCode: {
          200: function (response) {
            window.location.href = "http://localhost:8080/calculadora/calcular"
          },
          500: function (response) {
            alert('Erro ao logar. Verifique seu email ou senha');
            window.location.href = "http://localhost:8080/calculadora/login"
            window.location.reload();
          }
         }
    });
}