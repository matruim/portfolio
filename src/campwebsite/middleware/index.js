// All the middleware goes here
var middlewareObj = {};
var Campground = require("../models/campground");
var Comment = require("../models/comment");

middlewareObj.checkCampgroundOwnership = function(req, res, next){
    // is user logged in?
    if(req.isAuthenticated()){
        Campground.findById(req.params.id, function(err, foundCampground){
            if(err){
                req.flash("error", "Campground not found");
                res.redirect("back");
            } else{
                // does the user own the campground?
                if(foundCampground.author.id.equals(req.user._id)){
                    // You own it
                    next();
                }
                else{
                    // user doesn't own the campground
                    req.flash("error", "You dont have permission to do that.");
                    res.redirect("back");
                }
            }
            
        });
    }
    else{
        //send the user back to the previous page
        req.flash("error", "You need to be logged in to do that.");
        res.redirect("back");
    }
    
}

middlewareObj.checkCommentOwnership = function(req, res, next){
    // is user logged in?
    if(req.isAuthenticated()){
        Comment.findById(req.params.comment_id, function(err, foundComment){
            if(err){
                req.flash("error", "Campground not found");
                res.redirect("back");
            } else{
                // does the user own the comment?
                if(foundComment.author.id.equals(req.user._id)){
                    // You own it
                    next();
                }
                else{
                    // user doesn't own the comment
                    req.flash("error", "You dont have permission to do that.");
                    res.redirect("back");
                }
            }
            
        });
    }
    else{
        //send the user back to the previous page
        req.flash("error", "You need to be logged in to do that.");
        res.redirect("back");
    }
    
}

middlewareObj.isLoggedIn = function (req,res,next){
    if(req.isAuthenticated()){
        return next();
    }
    req.flash("error", "Please Login First!");
    res.redirect("/login");
}

module.exports = middlewareObj;