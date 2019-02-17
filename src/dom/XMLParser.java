package dom; /**
 * Copyright 2017 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.util.List;
import java.util.Stack;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
public class XMLParser {

    public DomTree parse(List<String> input){
        Stack<TagNode> stack = new Stack<>();
        DomTree last =null;
        for( String line:input){
            String[] part = line.split(",");
            if(part[0].equals("open")){
                TagNode tag = new TagNode();
                tag.start = "<"+part[1]+">";
                if(!stack.isEmpty()){
                    stack.peek().children.add(tag);
                }
                stack.push(tag);
            } else if (part[0].equals("close")){
                if(!stack.isEmpty()){
                    TagNode tag = stack.pop();
                    tag.end = "</"+part[1]+">";
                    last = tag;
                }
            } else {
                ContentNode node = new ContentNode();
                node.str = part[1];
                stack.peek().children.add(node);
            }
        }
        return last;
    }

//    public static void main(String[] arg){
//        //System.out.println("Hello");
//        XMLParser parser = new XMLParser();
//
//        List<String> input = new ArrayList<String>(Arrays.asList(
//                "open,story",
//                "open,id",
//                "inner,1234",
//                "close,id",
//                "open,snaps",
//                "open,snap",
//                "close,snap",
//                "open,snap",
//                "close,snap",
//                "open,snap",
//                "close,snap",
//                "open,snap",
//                "close,snap",
//                "close,snaps",
//                "close,story"
//        ));
//
//        DomTree dom = parser.parse(input);
//        print(dom);
//    }

    public static void print(DomTree dom){
        if( dom instanceof TagNode){
            System.out.println(((TagNode) dom).start);
            for( DomTree tag : ((TagNode)dom).children){
                print(tag);
            }
            System.out.print(((TagNode)dom).end);
        } else {
            System.out.print(((ContentNode)dom).str);
        }

    }
}
