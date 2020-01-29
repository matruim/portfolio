var express     = require("express"),
    app         = express(),
    bodyParser  = require("body-parser"),
    mongoose    = require("mongoose"),
    seedDB      = require("./seeds"),
    passport    = require("passport"),
    flash       = require("connect-flash"),
    methodOverride  = require("method-override"),
    LocalStrategy   = require("passport-local");

//set mongoose Promise to javascripts promise
mongoose.Promise = global.Promise; 
// connect to database
mongoose.connect(process.env.DATABASEURL, {useMongoClient: true});


app.set("view engine", "ejs");
app.use(bodyParser.urlencoded({extended: true}));
app.use(express.static(__dirname + '/public'));
app.use(methodOverride("_method"));
app.use(flash());

// Models
var Campground = require("./models/campground");
var Comment = require("./models/comment");
var User = require("./models/user");

// Passport Configuration
    app.use(require("express-session")({
        secret: "Once again Rusty wins cutest dog!",
        resave: false,
        saveUninitialized: false
    }));
    app.use(passport.initialize());
    app.use(passport.session());
    passport.use(new LocalStrategy(User.authenticate()));
    passport.serializeUser(User.serializeUser());
    passport.deserializeUser(User.deserializeUser());


//Clear and Seed DB
//seedDB();

//run the following function on all routes
app.use(function(req, res, next){
   res.locals.currentUser = req.user; 
   res.locals.error = req.flash("error");
   res.locals.success = req.flash("success");
   next();
});


// ====================================
//              ROUTES
// ====================================

    var commentRoutes = require("./routes/comments"),
        campgroundRoutes = require("./routes/campgrounds"),
        indexRoutes = require("./routes/index");


    app.use(indexRoutes);
    app.use("/campgrounds/:id/comments", commentRoutes);
    app.use("/campgrounds", campgroundRoutes);
    
    

// ====================================
//              LISTENER
// ====================================

app.listen(process.env.PORT,process.env.IP,function(){
    console.log("YelpCamp server has started");
    console.log(process.env.DATABASEURL);
});

