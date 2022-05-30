const ClassCultural = require('../models/class.model');
const ClassModel = require('../models/class.model');

// Get all Class Culture (GET)
exports.getClassList = (req, res) => {
    // console.log("Here all class list!")
    ClassModel.getAllClass((err, classes) => {
        if(err){
            res.send(err);
        }
            console.log('Classes', classes);
            res.send(classes)
    })
}

// Get Class By Id (GET)
exports.getClassListByID = (req, res) => {
    ClassModel.getClassByID(req.params.id, (err, classes) => {
        if(err)
        res.send(err);
        console.log('Class data ID:', classes);
        res.send(classes);
    })
}

// Create New Class(POST)
exports.createNewClass = (req, res) => {
    const classReqData = new ClassCultural(req.body);
    console.log('Req Data', classReqData)
    if(req.body.constructor === Object && Object.keys(req.body).length === 0){
        res.send(400).send({success: false, message: 'Please fill all fields!'});
    }else{
        ClassModel.createClass(classReqData, (err, classes) => {
        if(err)
            res.send(err);
            res.json({status: true, message: 'Class Created Successfull :)', data: classes})
            res.status(201).send("Data Created!")
        })
    }
}

// Update New Class (PUT)
exports.updateClass = (req, res) => {
    const classReqData = new ClassCultural(req.body);
    console.log('Req Data Update', classReqData)
    if(req.body.constructor === Object && Object.keys(req.body).length === 0){
        res.send(400).send({success: false, message: 'Please fill all fields!'});
    }else{
        ClassModel.updateClass(req.params.id, classReqData, (err, classes) => {
        if(err)
            res.send(err);
            res.json({status: true, message: 'Class Updated Successfull :)'})
        })
    }
}

// Delete Class (DELETE)
exports.deleteClass = (req, res) => {
    ClassModel.deleteClassId(req.params.id, (err, classes) => {
        if(err)
        res.send(err);
        res.json({success: true, message: 'Class Deleted Successfull :)'});
    })
}