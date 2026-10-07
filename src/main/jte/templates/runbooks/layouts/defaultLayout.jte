@import com.bharat.appointments.auth.AuthContext;

@param gg.jte.Content content
@param String title = "Appointments Management System"
@param boolean showTitle = true
@param AuthContext auth

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>
    <!-- Bootstrap CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/appointments.css" rel="stylesheet">
</head>
<body class="bg-light">
<main>
    <div class="container p-3 mt-4">
        @template.fragments.header(auth=auth)
        <div class="container-fluid mt-4 p-2 bg-light border-1">
            @if(showTitle && title != null && !title.isBlank())
            <h2 class="pb-3">${title}</h2>
            @endif
            ${content}
        </div>
    </div>
</main>
<!-- Bootstrap JS -->
<script src="/js/bootstrap.min.js"></script>
<script src="/js/htmx.min.js"></script>
</body>
</html>