var express = require("express");
var router = express.Router();
var User = require("../models/user");
var passport = require("passport");

// ====================================
//          ROOT Route
// ====================================
router.get('/',function(req,res){
    res.render("landing");
});


// ====================================
//             AUTH ROUTES
// ====================================
// ====================================
//              REGISTER
// ====================================
router.get("/register", function(req, res) {
    res.render("register");
});
router.post("/register",function(req, res) {
    var newUser = new User({username: req.body.username});
    User.register(newUser, req.body.password, function(err,user){
        if(err){
            console.log(err);
            req.flash("error", err.message);
            return res.redirect("/register");
        }
        passport.authenticate("local")(req,res, function(){
            req.flash("success", "Welcome to YelpCamp " + user.username);
            res.redirect("/campgrounds");
        });
    });
});

// ====================================
//               LOGIN
// ====================================
router.get("/login", function(req, res) {
    res.render("login");
});
router.post("/login", passport.authenticate("local",
    {
        successRedirect: "/campgrounds",
        failureRedirect: "/login"
        
    }), function(req, res) {
    
});

// ====================================
//               LOGOUT
// ====================================
router.get("/logout",function(req, res){
    req.logout();
    req.flash("success", "Logged you out!");
    res.redirect("/campgrounds");
});


module.exports = router;