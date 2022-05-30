const express = require("express");
const router = express.Router();

const classController = require('../controllers/class.controller');

// Get all class from class controller (GET)
router.get('/', classController.getClassList); 

// Get Class by Id (GET BY ID) 
router.get('/:id', classController.getClassListByID);

// Create New Class (POST)
router.post('/', classController.createNewClass);

// Update Class (PUT)
router.put('/:id', classController.updateClass);

// Delete Class (DELETE)
router.delete('/:id', classController.deleteClass);

module.exports = router;