
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.User"%>
<%@page import="Model.Historico"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="contentOuterSeparator"></div>

<div class="container">

    <div class="divPanel page-content">

        <div class="breadcrumbs">
            <a href="nw">Home</a> &nbsp;/&nbsp; <span>(${user.nome})</span>
        </div> 

        <div class="row-fluid">
            <!--Edit Main Content Area here-->
            <div class="span12" id="divMain">

                <!--                <h1>Página Inicial</h1>
                                <hr>-->
                <fieldset >
                    <legend style="margin-bottom: 0px">Painel de Controle</legend>
                    <div class="row-fluid">
                        <div class="span4">

                            <div class="form-top">
                                <div class="form-top-left">
                                    <h3>Dados Pessoais</h3>
                                    <!--<p>Informe seu peso e altura atual:</p>-->
                                </div>
                                <div class="form-top-right">
                                    <i class="fa fa-book"></i>
                                </div>
                            </div>
                            <div class="sidebox">

                                Nome: ${user.nome} <br />
                                IMC: <strong><fmt:formatNumber pattern="#.00" value="${user.historicoList[user.historicoList.size()-1].imc()}"/></strong> atual
                                <br />
                                <br />

                                <script type="text/javascript">
                                    google.charts.load('current', {'packages': ['corechart']});
                                    google.charts.setOnLoadCallback(drawChart);

                                    function drawChart() {

                                        var data = google.visualization.arrayToDataTable([
                                            ['Data', 'Peso', 'IMC'],
                                    <%
                                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                        //DecimalFormat df = new DecimalFormat("#0,00");
                                        String dt = "";
                                        for (Historico h : ((User) session.getAttribute("user")).getHistoricoList()) {
                                            dt += "['" + sdf.format(h.getDataMedida()) + "',"
                                                    + h.getPeso() + "," + h.imc() + "],";
                                        }
                                        out.print(dt.substring(0, dt.length() - 1));
                                    %>
                                        ]);

                                        var options = {
                                            title: 'Evolução Peso/IMC',
                                            legend: {position: 'rigth'},
                                            vAxis: {baseline: 0},
                                            hAxis: {baseline: 0, title: 'Data'},
                                            lineWidth: 4,
                                            width: 550,
                                            height: 400}

                                        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));

                                        chart.draw(data, options);
                                    }
                                </script>


                                <form action="" class="login_form modal" id="ex1" 
                                      style="display:none;">
                                    <h3>Gráficos</h3>
                                    <div id="chart_div"></div>
                                </form>
                                <form role="form" action="nw" method="post" class="login-form">
                                    <input type="hidden" name="ac" value="panelGrafico" />

                                    <a href="#ex1" id="bta1" rel="modal:open" style="display:none;">ex1</a>

                                    <button type="button" class="btn" onclick="$('#bta1').click()">Ver gráficos!</button>
                                </form>

                            </div>  

                            <p><label class="sr-only" for="cpAlimento"><h5>Alimentos Consumidos no dia </h5></label>
                            <form id="formFiltro" action="nw" method="post">
                                <input type="hidden" name="ac" value="panelFiltro" />
                                <select class="form-username form-control" id="cpDataFiltro" name="cpDataFiltro" 
                                        style="width: 200px">    
                                    <c:forEach items="${ListaDatas}" var="d">
                                        <option <c:if test="${dataFiltro==d}">selected="selected"</c:if> >
                                            <fmt:formatDate pattern="dd/MM/yyyy" value="${d}"/>
                                        </option>  
                                    </c:forEach>
                                </select>
                            </form>
                            </p>
                        </div>
                        <div class="span4">
                            <div class="form-top">
                                <div class="form-top-left">
                                    <h3>Objetivo</h3>
                                    <!--<p>Informe seu peso e altura atual:</p>-->
                                </div>
                                <div class="form-top-right">
                                    <i class="fa fa-tachometer"></i>
                                </div>
                            </div>
                            <div class="sidebox">
                                <form>

                                    <label class="sr-only" for="cpObjetivo1">
                                        <input type="radio" value="Emagrecer" 
                                               <c:if test="${user.objetivo=='Emagrecer'}">checked="checked"</c:if>
                                                   name="cpObjetivo"  id="cpObjetivo1" />
                                               Emagrecer</label><br />

                                        <label class="sr-only" for="cpObjetivo2">
                                            <input type="radio" value="Ganhar Massa" 
                                            <c:if test="${user.objetivo=='Ganhar Massa'}">checked="checked"</c:if>
                                                name="cpObjetivo" id="cpObjetivo2" />
                                            Ganhar Massa</label><br />

                                        <label class="sr-only" for="cpObjetivo3">
                                            <input type="radio" value="Melhorar Alimentação" 
                                            <c:if test="${user.objetivo=='Melhorar Alimentação'}">checked="checked"</c:if>
                                                name="cpObjetivo" id="cpObjetivo3" />
                                            Melhorar Alimentação</label>

                                        <!--<input type="hidden" name="ac" value="panelObjetivo" />-->
                                        <br />
                                        <br />
                                        <button type="button" id="btnControleDiario" class="btn" >Criar Controle Diário</button>

                                    </form>
                                    <form action="nw" class="login_form modal" id="ex2" method="POST"
                                          style="display:none;height: 350px; padding: 5px;">
                                        <h3>Consumo de Alimentos</h3>
                                        <input type="hidden" name="ac" value="NovoControleDiario"/>
                                        <input type="hidden" name="objetivo" id="idObj" value=""/>
                                        <label class="sr-only"  style="padding-left: 20px;">
                                            <h4>Deseja Criar um novo registro de Consumo de Alimentos?</h4>  
                                        </label> 
                                        <label class="sr-only"  style="padding-left: 40px;">
                                            <h5 id="objConsumo">Objetivo do Consumo: </h5> 
                                        </label> 
                                        <label class="sr-only"  style="padding-left: 40px;">
                                            <h5>Data do Consumo:   <input type="text" name="datepicker" id="datepicker"/></h5>
                                        </label> 
                                        <p></p>
                                        <p style="text-align: center">
                                            <a href="#" style="display:none;" id="closeWin" rel="modal:close">Close</a>
                                            <button type="submit" name="btnConsumo" class="btn" value="Sim">Sim</button>
                                            <button type="button" name="btnConsumo" onclick="$('#closeWin').click()" class="btn" value="Não">Não</button></p>
                                    </form>
                                    <a href="#ex2" id="bta2" rel="modal:open" style="display:none;">ex2</a>

                                </div>                                
                            </div>
                            <div class="span4">

                                <div id="divHeaderText" class="page-content">

                                    <div class="col-sm-6 col-sm-offset-3 form-box1">
                                        <div class="form-top">
                                            <div class="form-top-left">
                                                <h3>Gráficos</h3>
                                                <!--<p>Informe seu peso e altura atual:</p>-->
                                            </div>
                                            <div class="form-top-right">
                                                <i class="fa fa-pie-chart"></i>
                                            </div>
                                        </div>
                                        <div class="sidebox">
                                            <form role="form" action="nw" method="post" class="login-form">
                                                <input type="hidden" name="ac" value="panelPesoAltura" />
                                                <div class="form-group1">
                                                    <label class="sr-only" for="cpPeso">Peso (informe seu novo peso)</label>
                                                    <input type="number" name="cpPeso" required="required" placeholder="peso..." class="form-username form-control" id="form-peso"
                                                           value="${user.historicoList[user.historicoList.size()-1].peso}" step="any">
                                            </div>
                                            <div class="form-group1">
                                                <label class="sr-only" for="cpAltura">Altura (informe sua nova altura)</label>
                                                <input type="number" name="cpAltura" required="required" placeholder="altura..." class="form-username form-control" id="form-altura"
                                                       value="${user.historicoList[user.historicoList.size()-1].altura}" step="any">
                                            </div>
                                            <button type="submit" class="btn">OK!</button>
                                        </form>
                                    </div>
                                </div>

                            </div>

                        </div>

                    </div>

                </fieldset>		



            </div>
            <!--End Main Content Area-->
        </div>

        <div id="footerInnerSeparator"></div>
    </div>

</div>


<div id="contentOuterSeparator"></div>

<div class="container">

    <div class="divPanel page-content">
        <!--Edit Main Content Area here-->
        <div class="row-fluid">

            <div class="span12" id="divMain">
                <form action="nw" class="login_form modal" id="ex3" 
                      style="display:none;height: 350px">
                    <h3>Novo Alimento Consumido</h3>
                    <input type="hidden" name="ac" value="newAlimentoConsumido"/>
                    <input type="hidden" name="idhorrarioRefeicao" id="idRefei" value=""/>
                    <div id="dvAlimento">
                        <!--//injetado via ajax-->
                    </div>  
                </form>
                <a href="#ex3" id="bta3" rel="modal:open" style="display:none;">ex3</a>

                <c:set var="totalCalMax" value="0"/>
                <c:set var="totalConsumo" value="0"/>
                <table id="report" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>Tipo de Refeição</th>
                            <th>Horário</th>
                            <th>Calorias Máxima</th>
                            <th>Comsumo Calorico</th>
                            <th>Falta Consumir?</th>
                            <th></th>
                        </tr>
                    </thead>
                    <c:forEach items="${ListaconsumoDiario}" var="cd">                        
                        <tr>
                            <td>${cd.horrarioRefeicao.descricao}</td>
                            <td><fmt:formatNumber pattern="00 H" value="${cd.horario}"/></td>
                            <td><fmt:formatNumber pattern="#0.00" value="${cd.calMax}"/></td>
                            <c:set var="totalCalMax" value="${totalCalMax+cd.calMax}"/>
                            <c:set var="st" value="0"/>
                            <c:forEach items="${cd.consumoDiarioAlimentoList}" var="cda">
                                <c:set var="st" value="${st+(cda.qtde * cda.alimento.cal)}"/>    
                            </c:forEach>
                            <td><fmt:formatNumber pattern="#0.00" value="${st}"/></td>
                            <c:set var="totalConsumo" value="${totalConsumo+st}"/>
                            <td><fmt:formatNumber pattern="#0.00" value="${cd.calMax - st}"/></td>
                            <td><div class="arrow"></div></td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                Adicionar alimentos Comsumidos <br />
                                <div class="rTable">
                                    <div class="rTableRow">
                                        <div class="rTableHead"><strong>Alimento</strong></div>
                                        <div class="rTableHead"><strong>Quantidade</strong></div>
                                        <div class="rTableHead"><strong>Calorias</strong></div>
                                    </div>
                                    <c:if test="${cd.consumoDiarioAlimentoList.size()==0}">
                                        <div class="rTableRow">
                                            <div class="rTableCell">Não há Registros de Alimento</div>
                                        </div>
                                    </c:if>
                                    <c:forEach items="${cd.consumoDiarioAlimentoList}" var="cda">
                                        <div class="rTableRow">
                                            <div class="rTableCell">${cda.alimento.descricao}</div>
                                            <div class="rTableCell">${cda.qtde} ${cda.alimento.unidade}</div>
                                            <div class="rTableCell">${cda.qtde * cda.alimento.cal} cal</div>
                                        </div>
                                    </c:forEach> 
                                </div>
                            </td>
                            <td >
                                <div class="add" id="cd${cd.horrarioRefeicao.idhorrarioRefeicao}" ></div></td>
                        </tr>                    
                    </c:forEach>
                    <tfoot>
                        <tr>
                            <th colspan="2">TOTAL</th>
                            <th><fmt:formatNumber pattern="#0.00" value="${totalCalMax}"/></th>
                            <th><fmt:formatNumber pattern="#0.00" value="${totalConsumo}"/></th>
                            <th></th>
                            <th></th>
                        </tr>
                    </tfoot>
                </table>
            </div>

        </div>
        <!--End Main Content Area here-->

        <div id="footerInnerSeparator"></div>
    </div>

</div>

<script type="text/javascript">

    $(document).ready(function () {
        $("#report tr:odd").addClass("odd");
        $("#report tr:not(.odd)").hide();
        $("#report tr:first-child").show();

        $("#report tr.odd").click(function () {
            $(this).next("tr").toggle();
            $(this).find(".arrow").toggleClass("up");
        });

        $("#report tr:not(.odd) div.add").click(function (event) {
            event.preventDefault();
            //alert($(this).attr('id'));
//             $.get(this.href, function (html) {
//             $(html).appendTo('body').modal();
            $('#idRefei').val($(this).attr('id'));
            $.ajax({url: "nw?ac=ajaxAlimento", success: function (result) {
                    $("#dvAlimento").html(result);
                }});
            $('#bta3').click()
        });
        //$("#report").jExpand();

        $("#btnControleDiario").click(function (event) {
            event.preventDefault();

            $("#objConsumo").text("Objetivo do Consumo: " +
                    ($("#cpObjetivo1").is(":checked") ? $("#cpObjetivo1").val() :
                            $("#cpObjetivo2").is(":checked") ? $("#cpObjetivo2").val() :
                            $("#cpObjetivo3").val()));
            $("#idObj").val(($("#cpObjetivo1").is(":checked") ? $("#cpObjetivo1").val() :
                    $("#cpObjetivo2").is(":checked") ? $("#cpObjetivo2").val() :
                    $("#cpObjetivo3").val()));
            //alert( $("#idObj").val() );
            $('#bta2').click()
        });

        $("#datepicker").datepicker();
        $("#datepicker").datepicker("option", "dateFormat", "dd/mm/yy");

        $("#cpDataFiltro").change(function () {
            $("#formFiltro").submit();
        });

    });
</script>  

