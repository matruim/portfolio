var mongoose = require("mongoose");
var Campground = require("./models/campground");
var Comment   = require("./models/comment");

var data = [
    {
        name: "Cloud's Rest", 
        image: "https://farm4.staticflickr.com/3795/10131087094_c1c0a1c859.jpg",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus suscipit ultrices quam, nec ullamcorper tellus. Etiam lacinia ac est id iaculis. Mauris condimentum ex a maximus commodo. Sed ullamcorper nunc quis dictum laoreet. Curabitur odio erat, gravida vel lacus eu, vestibulum dapibus arcu. Quisque egestas ornare commodo. Donec blandit ex at lorem venenatis hendrerit. Pellentesque condimentum, nunc nec scelerisque dapibus, felis lacus varius ligula, sit amet fermentum mi leo id mauris. Donec viverra et lectus id scelerisque. Ut vitae scelerisque tellus."
    },
    {
        name: "Desert Mesa", 
        image: "https://c1.staticflickr.com/6/5479/14106262509_d37e9792db_b.jpg",
        description: "Ut molestie justo cursus massa convallis feugiat. Maecenas mattis mauris et lorem faucibus, lobortis elementum sapien egestas. In in lorem tempus, viverra leo ut, convallis odio. Sed ut orci luctus est tempor tempor. Cras lacus tortor, iaculis eu tortor id, rhoncus faucibus diam. Aenean hendrerit nisi malesuada est dignissim, laoreet suscipit massa convallis. Etiam nec ultrices risus, a accumsan eros. Nullam dapibus nisi a tempus dictum. Aliquam at efficitur metus, quis dapibus magna."
    },
    {
        name: "Canyon Floor", 
        image: "https://farm1.staticflickr.com/189/493046463_841a18169e.jpg",
        description: "Pellentesque suscipit posuere dignissim. Vivamus volutpat fermentum lorem, non laoreet lectus dignissim id. Vivamus quam justo, semper ut elit et, tincidunt egestas massa. Proin eleifend tempor nisi id volutpat. Curabitur ultrices iaculis urna quis finibus. Nunc venenatis commodo dui a eleifend. Praesent non ante eu arcu finibus hendrerit. Donec eu justo erat. Sed rhoncus ligula nec eros pellentesque gravida. Donec lacinia, urna eleifend tempor ultricies, sapien ipsum ultricies tortor, et rutrum arcu lacus sit amet eros. Nam non fringilla leo. Etiam euismod, est vitae tincidunt placerat, mauris dolor gravida ante, ultrices sagittis nibh quam ac odio. Aenean auctor augue semper mi pellentesque fermentum. Morbi ornare dignissim augue in porta. In et fermentum nunc, at elementum lorem. Quisque sodales sed eros vitae efficitur."
    }
]

function seedDB(){
   //Remove all campgrounds
   Campground.remove({}, function(err){
        if(err){
            console.log(err);
        }
        console.log("removed campgrounds!");
          //add a few campgrounds
         data.forEach(function(seed){
            Campground.create(seed, function(err, campground){
                 if(err){
                     console.log(err)
                 } else {
                     console.log("added a campground");
                     //create a comment
                     Comment.create(
                         {
                             text: "This place is great, but I wish there was internet",
                             author: "Homer"
                         }, function(err, comment){
                             if(err){
                                 console.log(err);
                             } else {
                                 campground.comments.push(comment);
                                 campground.save();
                                 console.log("Created new comment");
                             }
                         });
                  }
             });
         });
    }); 
    //remove all comments
    Comment.remove({},function(err){
       if(err){
           console.log(err);
       } 
    });
}

module.exports = seedDB;