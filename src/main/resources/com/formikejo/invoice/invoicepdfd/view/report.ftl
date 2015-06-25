<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="assets/ReportStyle.css">
    <link rel="stylesheet" type="text/css" href="assets/Color Sceme 1.css">
    <title>Invoice Template 1</title>
</head>
<body>

<div class = title>
<img class = img src="/assets/logos/Publysher.png">
    </div>

<p class="customer-name">${receiver.companyName}</p>

<p class="TxtBoxFactuurnummer">
    ${issueDate}<br>
    <#if receiver.orderReferenceID?has_content>Referentie: ${receiver.orderReferenceID}<br></#if>
    <#if id?has_content>Factuurnummer: ${id}</#if></p>

<p class= "TopRecieverBox"><#if receiver.name?has_content>t.a.v. ${receiver.name} <br></#if>
    ${receiver.streetName} <br>
    ${receiver.postalCode}
</p>

<table class="table">
    <thead>
    <tr>
        <td class="TableTitles">Omschrijving</td>
        <td class="TableTitlesamount"><b>Aantal</b></td>
        <td class="TableTitlesamount"><b>Tarief</b></td>
        <td class="TableTitlesamount"><b>Bedrag</b></td>
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
    <tr>
        <td class="spacer">
        <td>
        <td>
        <td>
    </tr>

    </tbody>
    <tfoot>
    <tr class="footspacer">
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
        <td class="totalAmount"><b>Totaal factuurbedrag</b>
        <td>
        <td>
        <td class="totalAmountamount">&euro; ${bill.total?string["#.00"]}
    </tr>
    </tfoot>
</table>

<p class="reminder">${paymentTerms}</p>

<p class="bottomCompanyName"> ${sender.companyName}

<p>

<p class="KvKreminder">
        <#if sender.companyIdentification?has_content>KvK: ${sender.companyIdentification}<br></#if>
        <#if sender.vatNumber?has_content>${bill.taxType} nummer: ${sender.vatNumber}<br></#if>
        <#if sender.bankAccount?has_content>Bank:${sender.bankAccount} </#if>
</p>

<p class="bottomAdress">${sender.address} <br> ${sender.postalCode} </p>

<p class="bottomTelephone">
    <#if sender.telephone?has_content>T: ${sender.telephone}<br></#if>
    <#if sender.email?has_content>E: ${sender.email} </#if>
</p>
</body>
</html>