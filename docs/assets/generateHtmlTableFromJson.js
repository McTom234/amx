const jsonData = require('./complexity.json');

function generateHtmlTableFromJson(json) {
    let htmlString = "";
    for (const jsonKey in json) {
        htmlString += `<table><tbody><tr><th>${jsonKey}</th></tr>`;

        const fields = json[jsonKey];
        for (const field in fields) htmlString += `<tr><td> - ${field} = ${fields[field]}</td></tr>`;
        htmlString += "</tbody></table>";
    }
    console.log(htmlString);
}

generateHtmlTableFromJson(jsonData)