const functions = require("firebase-functions");
const express = require('express');
const cors = require('cors');
const admin = require('firebase-admin');

var serviceAccount = require("./budayai-permission.json");

admin.initializeApp({
    credential: admin.credential.cert(serviceAccount),
    databaseURL: "https://budayai-c22-ps195-default-rtdb.asia-southeast1.firebasedatabase.app"
});

const db = admin.firestore();

const app = express();
app.use(cors ({origin:true}));


// Default Routes 
app.get('/', (req, res) => {
    res.json('message: BudayAI is Here!');
    res.status(200);
});

// POST Data Home
app.post('/api/v1/home', (req, res) => {
    (async () => {
        try{
            await db.collection('home').doc('/' + req.body.id + '/')
            .create({
                ethnic_name: req.body.ethnic_name,
                type: req.body.type,
                photo_url: req.body.photo_url
            })
            return res.status(200).json("message: data created successfully!");
        }
        catch(error){
            console.log(error);
            return res.status(500).send(error);
        }
    })();
});

// GET Data Home By ID
app.get('/api/v1/home/:id', (req, res) => {
    (async () => {
        try{
            const document = db.collection('home').doc(req.params.id);
            let home = await document.get();
            let response = home.data();
            res.status(200).send(response);
        }
        catch(error){
            console.log(error);
            res.status(500).send(error);
        }
    })();
});

// Read All Data Home (GET)
app.get('/api/v1/home', (req, res) => {
    (async () => {
        try{
            let query = db.collection('home');
            let response = [];

            await query.get().then(querySnapshot => {
                let docs = querySnapshot.docs // Result All Query 
                for (let doc of docs)
                {
                    const selectedItem = {
                        id: doc.id,
                        ethnic_name: doc.data().ethnic_name,
                        type: doc.data().type,
                        photo_url: doc.data().photo_url
                    };
                    response.push(selectedItem);
                }
                return response;
            })
            return res.status(200).send(response);
        }
        catch(error){
            console.log(error);
            res.status(500).send("Internal Server Error!", error);
        }
    })();
});


// Update data Home (PUT)
app.put('/api/v1/home/:id', (req, res) => {
    (async () => {
        try{
            const document = db.collection('home').doc(req.params.id);
            await document.update({
                ethnic_name: req.body.ethnic_name,
                type: req.body.type,
                photo_url: req.body.photo_url
            });
            return res.status(200).send('Data Home Updated Successfully!');
        }
        catch(error){
            console.log(error);
            return res.status(500).send('Internal Server Error!', error)
        }
    })();
});

// Delete data Home By ID (DELETE)
app.delete('/api/v1/home/:id', (req, res) => {
    (async () => {
        try{
            const document = db.collection('home').doc(req.params.id);
            await document.delete();

            return res.status(200).send('Data Home Deleted Successfully!');
        }
        catch(error){
            console.log(error);
            return res.status(500).send('Internal Server Error!', error);
        }
    })();
})

// -------------------------------Data Cultural--------------------------------------//

// POST Data Cultural
app.post('/api/v1/cultural', (req, res) => {
    (async () => {
        try{
            await db.collection('cultural').doc('/' + req.body.id + '/')
            .create({
                photo_url: req.body.photo_url,
                type: req.body.type,
                description: req.body.description
            })
            return res.status(200).json("message: data cultural created successfully!");
        }
        catch(error){
            console.log(error);
            return res.status(500).send(error);
        }
    })();
});

// GET Data Cultural By ID
app.get('/api/v1/cultural/:id', (req, res) => {
    (async () => {
        try{
            const document = db.collection('cultural').doc(req.params.id);
            let home = await document.get();
            let response = home.data();
            res.status(200).send(response);
        }
        catch(error){
            console.log(error);
            res.status(500).send(error);
        }
    })();
});

// Read All Data Cultural (GET)
app.get('/api/v1/cultural', (req, res) => {
    (async () => {
        try{
            let query = db.collection('cultural');
            let response = [];

            await query.get().then(querySnapshot => {
                let docs = querySnapshot.docs // Result All Query 
                for (let doc of docs)
                {
                    const selectedItem = {
                        id: doc.id,
                        photo_url: doc.data().photo_url,
                        type: doc.data().type,
                        description: doc.data().description
                    };
                    response.push(selectedItem);
                }
                return response;
            })
            return res.status(200).send(response);
        }
        catch(error){
            console.log(error);
            res.status(500).send("Internal Server Error!", error);
        }
    })();
});


// Update data Cultural (PUT)
app.put('/api/v1/cultural/:id', (req, res) => {
    (async () => {
        try{
            const document = db.collection('cultural').doc(req.params.id);
            await document.update({
                photo_url: req.body.photo_url,
                type: req.body.type,
                description: req.body.description
            });
            return res.status(200).send('Data Cultural Updated Successfully!');
        }
        catch(error){
            console.log(error);
            return res.status(500).send('Internal Server Error!', error)
        }
    })();
});

// Delete data Cultural By ID (DELETE)
app.delete('/api/v1/cultural/:id', (req, res) => {
    (async () => {
        try{
            const document = db.collection('cultural').doc(req.params.id);
            await document.delete();

            return res.status(200).send('Data cultural deleted successfully!');
        }
        catch(error){
            console.log(error);
            return res.status(500).send('Internal Server Error!', error);
        }
    })();
});


// -------------------------------Data Detail-------------------------------------- //

// POST Data Detail
app.post('/api/v1/detail', (req, res) => {
    (async () => {
        try{
            await db.collection('detail').doc('/' + req.body.id + '/')
            .create({
                photo_url: req.body.photo_url,
                type: req.body.type,
                detail: req.body.detail
            })
            return res.status(200).json("message: data detail created successfully!");
        }
        catch(error){
            console.log(error);
            return res.status(500).send(error);
        }
    })();
});

// GET Data Detail By ID
app.get('/api/v1/detail/:id', (req, res) => {
    (async () => {
        try{
            const document = db.collection('detail').doc(req.params.id);
            let home = await document.get();
            let response = home.data();
            res.status(200).send(response);
        }
        catch(error){
            console.log(error);
            res.status(500).send(error);
        }
    })();
});

// Read All Data Detail (GET)
app.get('/api/v1/detail', (req, res) => {
    (async () => {
        try{
            let query = db.collection('detail');
            let response = [];

            await query.get().then(querySnapshot => {
                let docs = querySnapshot.docs // Result All Query 
                for (let doc of docs)
                {
                    const selectedItem = {
                        id: doc.id,
                        photo_url: doc.data().photo_url,
                        type: doc.data().type,
                        detail: doc.data().detail
                    };
                    response.push(selectedItem);
                }
                return response;
            })
            return res.status(200).send(response);
        }
        catch(error){
            console.log(error);
            res.status(500).send("Internal Server Error!", error);
        }
    })();
});


// Update data Detail (PUT)
app.put('/api/v1/detail/:id', (req, res) => {
    (async () => {
        try{
            const document = db.collection('detail').doc(req.params.id);
            await document.update({
                photo_url: req.body.photo_url,
                type: req.body.type,
                detail: req.body.detail
            });
            return res.status(200).send('Data Detail Updated Successfully!');
        }
        catch(error){
            console.log(error);
            return res.status(500).send('Internal Server Error!', error)
        }
    })();
});

// Delete data Detail By ID (DELETE)
app.delete('/api/v1/detail/:id', (req, res) => {
    (async () => {
        try{
            const document = db.collection('detail').doc(req.params.id);
            await document.delete();

            return res.status(200).send('Data detail deleted successfully!');
        }
        catch(error){
            console.log(error);
            return res.status(500).send('Internal Server Error!', error);
        }
    })();
})

// -------------------------------Data Class-------------------------------------- //

// POST Data Class
app.post('/api/v1/class', (req, res) => {
    (async () => {
        try{
            await db.collection('class').doc('/' + req.body.id + '/')
            .create({
                type: req.body.type,
                detail: req.body.detail,
                history: req.body.history,
                photo_url: req.body.photo_url,
                lat: req.body.lat,
                lon: req.body.lon
            })
            return res.status(200).json("message: data class created successfully!");
        }
        catch(error){
            console.log(error);
            return res.status(500).send(error);
        }
    })();
});

// GET Data Class By ID
app.get('/api/v1/class/:id', (req, res) => {
    (async () => {
        try{
            const document = db.collection('class').doc(req.params.id);
            let home = await document.get();
            let response = home.data();
            res.status(200).send(response);
        }
        catch(error){
            console.log(error);
            res.status(500).send(error);
        }
    })();
});

// Read All Data Class (GET)
app.get('/api/v1/class', (req, res) => {
    (async () => {
        try{
            let query = db.collection('class');
            let response = [];

            await query.get().then(querySnapshot => {
                let docs = querySnapshot.docs // Result All Query 
                for (let doc of docs)
                {
                    const selectedItem = {
                        id: doc.id,
                        type: doc.data().type,
                        detail: doc.data().detail,
                        history: doc.data().history,
                        photo_url: doc.data().photo_url,
                        lat: doc.data().lat,
                        lon: doc.data().lon
                    };
                    response.push(selectedItem);
                }
                return response;
            })
            return res.status(200).send(response);
        }
        catch(error){
            console.log(error);
            res.status(500).send("Internal Server Error!", error);
        }
    })();
});


// Update data Class (PUT)
app.put('/api/v1/class/:id', (req, res) => {
    (async () => {
        try{
            const document = db.collection('class').doc(req.params.id);
            await document.update({
                type: req.body.type,
                detail: req.body.detail,
                history: req.body.history,
                photo_url: req.body.photo_url,
                lat: req.body.lat,
                lon: req.body.lon
            });
            return res.status(200).send('Data Class Updated Successfully!');
        }
        catch(error){
            console.log(error);
            return res.status(500).send('Internal Server Error!', error)
        }
    })();
});

// Delete data Class By ID (DELETE)
app.delete('/api/v1/class/:id', (req, res) => {
    (async () => {
        try{
            const document = db.collection('class').doc(req.params.id);
            await document.delete();

            return res.status(200).send('Data Class deleted successfully!');
        }
        catch(error){
            console.log(error);
            return res.status(500).send('Internal Server Error!', error);
        }
    })();
});

exports.app = functions.https.onRequest(app);