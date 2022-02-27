const jsonData = require('./example.json');

function generateHtmlTableFromJson(json) {
    let htmlString = "";
    for (const jsonKey in json) {
        htmlString += `<table><tr><th>${jsonKey}</th></tr>`;

        const fields = json[jsonKey];
        for (const field in fields) htmlString += `<tr><td> - ${field} = ${fields[field]}</td></tr>`;
        htmlString += "</table>";
    }
    console.log(htmlString);
}

generateHtmlTableFromJson(jsonData)