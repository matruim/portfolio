var express = require("express");
var router = express.Router();
var Campground = require("../models/campground");
var middleware = require("../middleware");

// ====================================
//          INDEX
// ====================================
router.get('/',function(req,res){
    //find all campgrounds in the database
    Campground.find({}, function(err, campgrounds){
        if(err){
            console.log("Error: " + err);
        }else{
            res.render("campgrounds/index",{campgrounds:campgrounds});
        }
        
    });
});
// ====================================
//          NEW
// ====================================
router.get('/new',middleware.isLoggedIn,function(req,res){
    res.render('campgrounds/new.ejs');
});
// ====================================
//          CREATE
// ====================================
router.post("/",middleware.isLoggedIn,function(req,res){
    //get data from form and add to campgrounds array
    var name = req.body.name;
    var price = req.body.price;
    var image = req.body.image;
    var desc = req.body.description;
    var author = {
        id: req.user._id,
        username: req.user.username
    }
    var newCampground = {name: name, image: image, description: desc, author: author, price: price};
    
    //create a new campground and save to database
    Campground.create(newCampground,function(err, newCamp){
        if(err){
            req.flash("error", "Something went wrong try again.");
            res.redirect("back");
        }else{
            // redirect back to campgrounds page
            req.flash("success", "Campground Created");
            res.redirect("/campgrounds");
        }
    })
});
// ====================================
//          SHOW
// ====================================
router.get("/:id",function(req, res){
    // find campground with provided id and populate all the comments on that item
    Campground.findById(req.params.id).populate("comments").exec(function(err, foundCampground){
        if(err){
            req.flash("error", "Something went wrong try again.");
            return res.redirect("back");
        }else{
            // render the show template with that campground
            res.render("campgrounds/show", {campground: foundCampground});
        }
    });
    
});
// ====================================
//              EDIT
// ====================================
router.get("/:id/edit", middleware.checkCampgroundOwnership, function(req, res) {
    Campground.findById(req.params.id, function(err, foundCampground){
        if(err){
            req.flash("error", "Something went wrong try again.");
            res.redirect("back");
        }
        res.render("campgrounds/edit", {campground: foundCampground});
    });
});
// ====================================
//              UPDATE
// ====================================
router.put("/:id", middleware.checkCampgroundOwnership, function(req,res){
    // find and update the correct campground
    Campground.findByIdAndUpdate(req.params.id, req.body.campground, function(err, updatedCampground){
        if(err){
            req.flash("error", "Something went wrong try again.");
            return res.redirect("/campgrounds");
        }
        req.flash("success", "Campground Updated");
        res.redirect("/campgrounds/" + req.params.id);
    });
});
// ====================================
//              DESTROY
// ====================================
router.delete("/:id", middleware.checkCampgroundOwnership, function(req,res){
    Campground.findByIdAndRemove(req.params.id,function(err){
        if(err){
            req.flash("error", "Something went wrong try again.");
            return res.redirect("/campgrounds");
        }
        req.flash("success", "Campground has been Deleted");
        res.redirect("/campgrounds");
    });
});

// ====================================
//              EXPORTS
// ====================================
module.exports = router;