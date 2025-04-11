
<div id="decorative1" style="position:relative">
    <div class="container">

        <div class="divPanel headerArea">
            <div class="row-fluid">
                <div class="span12">

                    <div id="divHeaderText" class="page-content">

                        <div class="col-sm-6 col-sm-offset-3 form-box">
                            <div class="form-top">
                                <div class="form-top-left">
                                    <h3>Usuário</h3>
                                    <p>cadastre-se para ter acesso ao sistema:</p>
                                </div>
                                <div class="form-top-right">
                                    <i class="fa fa-user fa-fw"></i>
                                </div>
                            </div>
                            <div class="form-bottom">

                                <form role="form" action="nw" method="post" class="login-form">
                                    <input type="hidden" name="ac" value="cadUser" />
                                    <div class="form-group">
                                        <label class="sr-only" for="cpNome">Nome</label>
                                        <input type="text" name="cpNome" required="required" placeholder="nome..." class="form-username form-control" id="form-nome"
                                               value="${userCad.nome}">
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="cpPeso">Peso</label>
                                        <input type="number" name="cpPeso" required="required" placeholder="peso..." class="form-username form-control" id="form-peso"
                                               value="${userCad.historicoList[0].peso}" step="any">
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="cpAltura">Altura</label>
                                        <input type="number" name="cpAltura" required="required" placeholder="altura..." class="form-username form-control" id="form-altura"
                                               value="${userCad.historicoList[0].altura}" step="any">
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="cpUsername">Email</label>
                                        <input type="email" name="cpUsername" required="required" placeholder="email..." class="form-username form-control" id="form-username"
                                               value="${userCad.email}">
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="cpPassword1">Password</label>
                                        <input type="password" name="cpPassword1" required="required" placeholder="senha..." class="form-password form-control" id="cpPassword1">
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="cpPassword">Repetir Password</label>
                                        <input type="password" name="cpPassword" required="required" placeholder="senha..." class="form-password form-control" id="cpPassword">
                                    </div>
                                    <div id="pass-info" style="color: #fff !important"></div>
                                    <button type="submit" class="btn">Gravar!</button>
                                </form>
                            </div>
                        </div>

                    </div>

                </div>
            </div>

        </div>

    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        //alert("oi");
        var password1 = $('#cpPassword1');
        var password2 = $('#cpPassword');
        var passwordsInfo = $('#pass-info');

        // passwordStrengthCheck(password1, password2, passwordsInfo);

//    function passwordStrengthCheck(password1, password2, passwordsInfo) {
        var WeakPass = /(?=.{5,}).*/;
        var MediumPass = /^(?=\S*?[a-z])(?=\S*?[0-9])\S{5,}$/;
        var StrongPass = /^(?=\S*?[A-Z])(?=\S*?[a-z])(?=\S*?[0-9])\S{5,}$/;
        var VryStrongPass = /^(?=\S*?[A-Z])(?=\S*?[a-z])(?=\S*?[0-9])(?=\S*?[^\w\*])\S{5,}$/;

        $(password1).on('keyup', function (e) {
            if (VryStrongPass.test(password1.val())) {
                passwordsInfo.removeClass().addClass('vrystrongpass').html("Muito Forte! (Impressionante, por favor não se esqueça da password!)");
            }
            else if (StrongPass.test(password1.val())) {
                passwordsInfo.removeClass().addClass('strongpass').html("Forte! (Insira caracteres especiais para tornar ainda mais forte");
            }
            else if (MediumPass.test(password1.val())) {
                passwordsInfo.removeClass().addClass('goodpass').html("Bom! (Ponha letras maiúsculas)");
            }
            else if (WeakPass.test(password1.val())) {
                passwordsInfo.removeClass().addClass('stillweakpass').html("Algo fraco! (Digitos aumentar a força da password)");
            }
            else {
                passwordsInfo.removeClass().addClass('weakpass').html("Muito fraca! (Deve ter 5 ou mais caracteres)");
            }
        });

        $(password2).on('keyup', function (e) {
            if (password1.val() !== password2.val()) {
                passwordsInfo.removeClass().addClass('weakpass').html("As passwords não são iguais!");
            } else {
                passwordsInfo.removeClass().addClass('goodpass').html("Passwords iguais!");
            }
        });

    });
</script>

