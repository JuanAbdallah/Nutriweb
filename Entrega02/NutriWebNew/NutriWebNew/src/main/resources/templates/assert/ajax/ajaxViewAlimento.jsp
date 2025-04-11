
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<p><label class="sr-only" for="cpAlimento">Alimento:</label>
    <select class="form-username form-control" id="cpAlimento" name="cpAlimento" 
            style="width: 400px">    
        <option value="">Selecione...</option>
        <c:forEach items="${listaAlimentos}" var="a">
            <option value="${a.idalimento}">${a.descricao}</option>  
        </c:forEach>
    </select>
</p>
<p><label class="sr-only" for="cpQtde">Quantidade:</label>
    <input type="number" class="form-username form-control" id="cpQtde" name="cpQtde"  
           style="width: 400px" min="0" value="0"
           required="required" placeholder="quantidade..."/>
</p>
<p><button type="submit" class="btn">OK!</button></p>
