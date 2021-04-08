const express = require('express');
const roomRouter = express.Router();

const rooms = [
    {
        id: 1,
        name: 'GENERAL'
    },
    {
        id: 2,
        name: 'SPORTS'
    },
    {
        id: 3,
        name: 'GAMES'
    },
]

// route for get rooms
roomRouter.get('/', (req,res) => {
    res.send(rooms)
})
roomRouter.post('/room', (req, res) => {
    const newRoom = req.body
    if (!newUser.username) return res.send({ code: 400, message: 'Data is required' })
    console.log(`room ${newRoom}`)
    
    // ChatRedis
    //     .getUser(newUser.username, config.KEY)
    //     .then(user => {
    //         if (!user) {
    //             ChatRedis.addUser(newUser.username, config.KEY, newUser)
    //             console.log(`User ${newUser.username} logged`)
    //             return res.send({ code: 200, message: 'Logged in succesfully' })
    //         }

    //         console.log(`User ${newUser.username} already exists!!`)
    //         return res.send({ code: 401, message: 'Username already exists' })
    //     })
})
module.exports = roomRouter