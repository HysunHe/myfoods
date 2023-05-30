cd /home/hysunhe/projects/shard/dev/myfoods/app > /dev/null

rm -f myfoods.war > /dev/null

jar -cvf myfoods.war *

mv myfoods.war ../

cd - > /dev/null
