let express =   require('express'),
    app     =   express(),
    port    =   process.env.PORT || 3000,
    ip      =   process.env.IP || '127.0.0.1';

app.use(express.static(__dirname+'/public'));
app.use(express.static(__dirname+'/views'));

app.get('/',(req,res)=>{
    res.sendFile('index.html');
});
app.get('/resume',(req,res)=>{
    res.sendFile('resume.html');
})
app.get('/hobbies', (req,res)=>{
    res.sendFile('hobbies.html');
});

app.listen(port,ip,function(){
    console.log("Server is now running on " + ip + ":"+port);
});