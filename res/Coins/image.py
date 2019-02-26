from PIL import Image, ImageOps

def add_border(input_image, output_image, border, color=0):
    img = Image.open(input_image)

    if isinstance(border, int) or isinstance(border, tuple):
        bimg = ImageOps.expand(img, border=border, fill=color)
    else:
        raise RuntimeError('Border is not an integer or tuple!')

    bimg.save(output_image)
    
########################################################################################

def add_border(input_image, output_image, max_width, max_height, color=0):
    img = Image.open(input_image)
    
    width, height = img.size
    
    LR_Border = (max_width - width) // 2
    TB_Border = (max_height - height) // 2
    
    border=(LR_Border, TB_Border)

    if isinstance(border, int) or isinstance(border, tuple):
        bimg = ImageOps.expand(img, border, fill=color)
    else:
        raise RuntimeError('Border is not an integer or tuple!')

    bimg.save(output_image)
    
#####################################################################################

def add_multiple_borders(input_images, max_width, max_height, color=0):
    for x in input_images:
        
        img = Image.open(x)

        width, height = img.size

        LR_Border = (max_width - width) // 2
        TB_Border = (max_height - height) // 2

        border=(LR_Border, TB_Border)

        if isinstance(border, int) or isinstance(border, tuple):
            bimg = ImageOps.expand(img, border, fill=color)
        else:
            raise RuntimeError('Border is not an integer or tuple!')
        
        #Overrides input_image name
        bimg.save(x)
    
##########################################################################################

imgList = []

for x in range(8):
    imgList.append("coin_0" + str(x + 1) + ".png")


add_multiple_borders(imgList, max_width=48, max_height=48, color='rgba(0,0,0,0)')





