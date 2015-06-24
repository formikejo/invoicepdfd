<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/assets/ReportStyle.css">
    <title>Invoice Template 1</title>
</head>
<body>
<h1 class="title">Publysher</h1>

<h2 class="subTitle">Factuur </h2>

<p class="customer-name">${receiver.companyName}</p>

<p class="TxtBoxFactuurnummer">${issueDate}<br>Referentie: ${receiver.orderReferenceID}<br>Factuurnummer: ${id}</p>

<p>t.a.v. ${receiver.name} <br> ${receiver.streetName} <br> ${receiver.postalCode}</p>

<table class="table">
    <thead>
    <tr>
        <td><b>Omschrijving</b></td>
        <td class="amount"><b>Aantal</b></td>
        <td class="amount"><b>Tarief</b></td>
        <td class="amount"><b>Bedrag</b></td>
    </tr>
    </thead>
    <tbody>
    <#list bill.invoiceLines as line>
        <tr>
            <td>${line.description}</td>
            <td class="amount"> ${line.amount?string["0.##"]}</td>
            <td class="amount"> &euro;${line.rate?string["#.00"]}</td>
            <td class="amount"> &euro;${line.total?string["#.00"]}</td>
        </tr>
    </#list>

    </tbody>
    <tfoot>
    <tr class="footspacer">
        <td>
    </tr>
    <tr>
        <td>Subtotaal (exclusief ${bill.taxType})
        <td>
        <td>
        <td class="subtotalRow"><span>&euro; ${bill.subTotal?string["#.00"]}</span>
    </tr>
    <tr>
        <td>${bill.taxType} ${bill.taxPercent?string["#.00"]}%
        <td>
        <td>
        <td class="amount">&euro; ${bill.tax?string["#.00"]}
    </tr>
    <tr class="totalAmount">
        <td><b>Totaal factuurbedrag</b>
        <td>
        <td>
        <td class="totalAmount"><b>&euro; ${bill.total?string["#.00"]}</b>
    </tr>
    </tfoot>
</table>

<p class="reminder">${paymentTerms}</p>

<p class="bottomCompanyName"> ${sender.companyName}

<p>

<p class="KvKreminder">KvK: ${sender.companyIdentification}<br> ${bill.taxType} nummer: ${sender.vatNumber}<br>Bank:
    ${sender.bankAccount}</p>

<p class="bottomAdress">${sender.address} <br> ${sender.postalCode} </p>

<p class="bottomTelephone">T: ${sender.telephone} <br>E: ${sender.email} </p>
</body>
</html>