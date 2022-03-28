<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.bo.Message" %>
<%@ page import="com.bo.GameState" %><%--
  Created by IntelliJ IDEA.
  User: walid
  Date: 3/13/2022
  Time: 1:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>User home</title>
    <link href="${pageContext.request.contextPath}/style/bootstrap.min.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/style/style.css"
          rel="stylesheet">
</head>
<body>
<header>

    <div class="number">
        <div class="mysteryNumber">
            <img src="${pageContext.request.contextPath}/dices/dice-${result}.png">
        </div>
    </div>

    <div class="title-description">
        <div class="title">
            <h1>
                game Over

            </h1>
        </div>
        <div class="discription">
            <div>Press reset to play again</div>
        </div>
    </div>
    <form class=" btn reset" action="${pageContext.request.contextPath}/DeconnectServlet" >
        <button type="submit" class=" btn reset">Log out</button>
    </form>
</header>

<form action="${pageContext.request.contextPath}/back/ReinitGameServlet" >
    <button type="submit" class=" btn reset">Reset</button>
</form>


<main>
    <section class="right">
        <p class="message">
            Roll the dice</p>
        <p><i class="fas fa-award"></i> score:
            <span class="score">${user.getScore()}</span>
        </p>
        <p><i class="fas fa-trophy"></i> high score:
            <span class="highscore">${user.getBestScore()}</span>
        </p>
    </section>
    <form class=" btn reset" id="best" action="${pageContext.request.contextPath}/back/BestScoreServlet" >
        <button type="submit" class=" btn reset">Show best Score</button>
    </form>
</main>

</body>
</html>
