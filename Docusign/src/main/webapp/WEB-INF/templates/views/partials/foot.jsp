<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</content>
</div>
<!-- The opening div for the container is in head.ejs -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->

<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/lodash@4.17.5/core.min.js"></script>

<script src="/assets/notify.min.js"></script>
<!-- see https://notifyjs.jpillora.com/ -->
<script src="/assets/eg_03.js"></script>

<!-- Data from the server -->
<div id="server_data" data-server-data='{"flash": "${locals.messages}"}'
     class="hidden"></div>
</body>
</html>
