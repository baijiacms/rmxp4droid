require 'zlib'

#load scripts
data = load_data(Ycore.getScript);
#convert and eval
for rb in data
  #puts rb[1];
  code=Zlib::Inflate.inflate(rb[2])
  eval(code);
end
