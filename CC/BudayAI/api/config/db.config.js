const mysql = require('mysql');

const dbConnection = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'budayai'
});

dbConnection.connect((error) => {
    if(error) throw error;
    console.log(`Database Connected!`)
})

module.exports = dbConnection;