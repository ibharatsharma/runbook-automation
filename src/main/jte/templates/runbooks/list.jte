@model java.util.List<com.bharat.runbook_automation.model.Runbook>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Runbooks</title>
    <link href="/static/bootstrap.min.css" rel="stylesheet">
</head>

<body class="container py-4">

<h1 class="mb-4">Runbooks</h1>

<table class="table table-striped">
    <thead>
    <tr>
        <th>Title</th>
        <th>Type</th>
        <th>Status</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    @for(rb : model)
        <tr>
            <td>${rb.title()}</td>
            <td>${rb.typeId()}</td>
            <td>${rb.statusId()}</td>
            <td>
                <a class="btn btn-sm btn-primary" href="/runbooks/${rb.id()}">View</a>
            </td>
        </tr>
    @end
    </tbody>
</table>

</body>
</html>
