require 'java'
# Loads the data file indicated by filename and restores the object.
def load_dataE(filename)
 
  File.open(filename, "rb") { |f|
    if Ycore.getLoadDataMap(filename)==nil
    obj = Marshal.load(f)
    Ycore.putLoadDataMap(filename,obj)
    return obj
   else
   	obj=Ycore.getLoadDataMap(filename)
   end
  }
end

# Saves the object obj to the data file indicated by filename.
def save_dataE(obj, filename) 
  File.open(filename, "wb") { |f|
    Marshal.dump(obj, f)
  }
end
# Loads the data file indicated by filename and restores the object.
def load_data(filename)

  File.open(File.join(Ycore.getPath.getAbsolutePath,filename), "rb") { |f|
   if Ycore.getLoadDataMap(filename)==nil
    obj = Marshal.load(f)
    Ycore.putLoadDataMap(filename,obj)
     return obj
   else
   	obj=Ycore.getLoadDataMap(filename)
   end
  }
end

# Saves the object obj to the data file indicated by filename.
def save_data(obj, filename) 
  File.open(File.join(Ycore.getPath.getAbsolutePath,filename), "wb") { |f|
    Marshal.dump(obj, f)
  }
end

# Print something in a Dialog window
def print(*args)

end

# Print something in a Dialog window
def p(*args)

end