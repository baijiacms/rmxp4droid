require 'zlib'

#load scripts
data = load_data(Ycore.getScript)

names=[]
fileInt=0
#convert and eval
for rb in data
  names<<(fileInt.to_s+".rb")
  code =[Zlib::Inflate.inflate(rb[2])]
 	f=File.new(File.join(Ycore.ScriptCacheFolder,fileInt.to_s+".rb"), "w+") 
	f.puts(code) 
	f.close
	code=nil
	fileInt+=1
end
f=File.new(File.join(Ycore.ScriptCacheFolder,Ycore.ScriptCacheIndex), "w+") 
f.puts(names) 
f.close
names=nil