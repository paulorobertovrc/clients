function consultarRestaurantes() {
    $.ajax({
        url: 'http://localhost:8080/restaurantes',
        method: 'GET',
        success: function(response) {
            $("#conteudo").text(JSON.stringify(response));
        },
        error: function() {
            console.log('Erro ao consultar restaurantes');
        }
    });
}

function consultar() {
    $.ajax({
      url: "http://localhost:8080/formas-pagamento",
      type: 'GET',  
      success: function(response) {
        preencherTabela(response);
      }
    });
  }
  
function preencherTabela(formasPagamento) {
    $("#tabela tbody tr").remove();
  
    $.each(formasPagamento, function(i, formaPagamento) {
      var linha = $("<tr>");

      var linkAcao = $("<a href='#'>")
        .text("Excluir")
        .click(function(event) {
            event.preventDefault();
            excluir(formaPagamento.id);
        });
  
      linha.append(
        $("<td>").text(formaPagamento.id),
        $("<td>").text(formaPagamento.descricao),
        $("<td>").append(linkAcao)
      );
  
      linha.appendTo("#tabela");
    });
  }

function cadastrar() {
    var formaPagamento = {
      descricao: $("#campo-descricao").val()
    };
  
    $.ajax({
      url: "http://localhost:8080/formas-pagamento",
      type: 'POST',
      data: JSON.stringify(formaPagamento),
      contentType: "application/json",
  
      success: function() {
        alert("Forma de pagamento cadastrada com sucesso!");
        consultar();
      }, 
      error: function(response) {
        if (response.status == 400) {
            var problem = JSON.parse(response.responseText);
            alert(problem.detail);
        } else {
            alert("Erro ao cadastrar forma de pagamento");
        }
      }
    });
}

function excluir(id) {
    $.ajax({
      url: "http://localhost:8080/formas-pagamento/" + id,
      type: 'DELETE',
  
      success: function() {
        consultar();
        alert("Forma de pagamento excluída com sucesso!");
      }, 
      error: function(response) {
        // tratando erros da classe 4xx
        if (response.status >= 400 && response.status <= 499) {
            var problem = JSON.parse(response.responseText);
            alert(problem.detail);
        } else {
            alert("Erro ao excluir forma de pagamento");
        }
      }
    });
}

function abrirRestaurante() {
    $.ajax({
        url: 'http://localhost:8080/restaurantes/1/abertura',
        method: 'PUT',
        success: function(response) {
            $("#conteudo").text(JSON.stringify(response));
        },
        error: function() {
            console.log('Erro ao abrir restaurante');
        }
    });
}

function fecharRestaurante() {
    $.ajax({
        url: 'http://localhost:8080/restaurantes/1/fechamento',
        method: 'PUT',
        success: function(response) {
            $("#conteudo").text(JSON.stringify(response));
        },
        error: function() {
            console.log('Erro ao fechar restaurante');
        }
    });
}

$("#botao").click(consultarRestaurantes);
$("#botao-abertura").click(abrirRestaurante);
$("#botao-fechamento").click(fecharRestaurante);

$("#btn-consultar").click(consultar);
$("#btn-cadastrar").click(cadastrar);
