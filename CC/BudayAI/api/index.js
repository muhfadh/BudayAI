const express = require('express');
const bodyParser = require('body-parser');
const app = express();
const PORT = process.env.PORT || 3000;

// Parse request data type application/w-xxx-form-rulencoded
app.use(bodyParser.urlencoded({extended: false}));

// Parse request data type application/json
app.use(bodyParser.json());

// Define root default
app.get('/', (req, res) => {
    res.send("Hello! BudayAI is Here!");
})

// import class routes
const classRoutes = require('./src/routes/class.route');

// Create class routes
app.use('/api/v1/class', classRoutes); 

app.listen(PORT, () => {
    console.log(`Server is running on http://localhost${PORT}`)
})