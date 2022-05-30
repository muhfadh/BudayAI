let dbConnection = require ('../../config/db.config');

let ClassCultural = function (classCultural) {
    this.class_cultural = classCultural.class_cultural;
    this.detail = classCultural.detail;
    this.history = classCultural.history;
    this.latitude = classCultural.latitude;
    this.longtitude = classCultural.longtitude;
    this.created_at = new Date();
    this.updated_at = new Date();
}

// Get all class (GET)
ClassCultural.getAllClass = (result) => {
    dbConnection.query('SELECT * FROM class', (err, res) => {
        if(err){
            console.log('Error while fetch the Class Cultural', err);
            result(null, err);
        }else{
            console.log('Class Cultural fetched successful!');
            result(null, res);
        }
    })
}

// Get Class Cultural By ID (GET BY ID)
ClassCultural.getClassByID = (id, result) => {
    dbConnection.query('SELECT * FROM class WHERE id=?', id, (err, res) => {
        if(err){
            console.log('Error while fetching class by ID!', err)
            result(null, err);
        }else{
            console.log('Class Cultural by ID successful!');
            result(null, res);
        }

    })
}

// Create New Class (POST)
ClassCultural.createClass = (classData, result) => {
    dbConnection.query('INSERT INTO class SET ?', classData, (err, res) => {
        if(err){
            console.log('Error while inserting data');
            result(null, err)
        }else {
            console.log('Class Created Successful!');
            result(null, res)
        }
    })
}

// Create Update Class (PUT)
ClassCultural.updateClass = (id, classData, result) => {
    dbConnection.query("UPDATE class SET class_cultural=?,detail=?,history=?,latitude=?,longtitude=? WHERE id = ?", [classData.class_cultural,classData.detail,classData.history,classData.latitude,classData.longtitude, id], (err, res) => {
        if(err){
            console.log('Error while updating data class Cultural');
            result(null, err);
        }else {
            console.log('Class Cultural Updated Successful!');
            result(null, res);
        }
    })
}

// Delete Class (DELETE)
ClassCultural.deleteClassId = (id, result) => {
    dbConnection.query("DELETE FROM class WHERE id = ?", [id], (err, res) => {
        if(err){
            console.log('Error while delete the Class Cultural!');
            result(null, err);
        }else{
            console.log('Data Deleted Successfull!');
            result(null, res);
        }
    })
}

module.exports = ClassCultural;