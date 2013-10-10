function getFileExtension(i) {
    
    // i will be a string, but it may not have a file extension.
    // return the file extension (with no period) if it has one, otherwise false
    
    if(i.indexOf('.') !== -1)
    {
        var ext = i.split('.').pop();
        return ext;
    }
    else {
        return false;
    }
    
}
