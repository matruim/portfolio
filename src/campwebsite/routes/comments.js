var express = require("express");
var router = express.Router({mergeParams: true});
var Campground = require("../models/campground");
var Comment = require("../models/comment");
var middleware = require("../middleware");

// ====================================
//          COMMENTS Routes
// ====================================
// ====================================
//          NEW
// ====================================
router.get('/new', middleware.isLoggedIn,function(req,res){
    // find campground by id
    Campground.findById(req.params.id, function(err,campground){
        if(err){
            req.flash("error", "Something went wrong try again.");
            res.redirect("back");
        }
        else{
            console.log("New Comments Page Loaded.");
            res.render("comments/new", {campground: campground})
        }
    });
});
// ====================================
//          CREATE
// ====================================
router.post("/", middleware.isLoggedIn, function(req,res){
    // lookup campground using id
    Campground.findById(req.params.id, function(err,campground){
        if(err){
            req.flash("error", "Something went wrong try again.");
            res.redirect("back");
        }
        else{
            // create new comment
            Comment.create(req.body.comment, function(err, comment){
                if(err){
                    console.log(err)
                }
                else{
                    //add username and id to comment
                    comment.author.id = req.user._id;
                    comment.author.username = req.user.username;
                    //save comment
                    comment.save();
                    // connect new comment to campground
                    campground.comments.push(comment);
                    campground.save();
                    // redirect to show page of campground
                    req.flash("success", "You created a new Comment");
                    res.redirect("/campgrounds/" + campground._id);
                }
            });
        }
    });
});
// ====================================
//              EDIT
// ====================================
router.get("/:comment_id/edit", middleware.checkCommentOwnership, function(req, res) {
    Comment.findById(req.params.comment_id, function(err, foundComment){
        if(err){
            req.flash("error", "Something went wrong try again.");
            return res.redirect("back");
        }
        
        res.render("comments/edit", {comment: foundComment, campground_id: req.params.id});
    });
});
// ====================================
//              UPDATE
// ====================================
router.put("/:comment_id", middleware.checkCommentOwnership,function(req,res){
    // find and update the correct Comment
    Comment.findByIdAndUpdate(req.params.comment_id, req.body.comment, function(err, updatedComment){
        if(err){
            req.flash("error", "Something went wrong try again.");
            return res.redirect("back");
        }
        req.flash("success", "Comment Updated");
        res.redirect("/campgrounds/" + req.params.id);
    });
});
// ====================================
//              DESTROY
// ====================================
router.delete("/:comment_id", middleware.checkCommentOwnership, function(req,res){
    Comment.findByIdAndRemove(req.params.comment_id,function(err){
        if(err){
            req.flash("error", "Something went wrong try again.");
            return res.redirect("back");
        }
        req.flash("success", "Comment Deleted");
        res.redirect("/campgrounds/" + req.params.id);
    });
});

module.exports = router;