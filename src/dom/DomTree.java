package dom; /**
 * Copyright 2017 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
class DomTree {
}

class ContentNode extends DomTree {
    String str;

    @Override
    public String toString(){
        return str;
    }

}

class TagNode extends DomTree {

    String start,end;
    List<DomTree> children = new ArrayList<>();

    public List<DomTree> getChildren(){
        return children;
    }

    public void setChildren(List<DomTree> children){
        this.children = children;
    }

    @Override
    public String toString(){
        return start+children.toString()+end;
    }

}
