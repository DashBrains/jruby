/*
 * Copyright (c) 2013, 2014 Oracle and/or its affiliates. All rights reserved. This
 * code is released under a tri EPL/GPL/LGPL license. You can use it,
 * redistribute it and/or modify it under the terms of the:
 *
 * Eclipse Public License version 1.0
 * GNU General Public License version 2
 * GNU Lesser General Public License version 2.1
 */
package org.jruby.truffle.nodes.objectstorage;

import com.oracle.truffle.api.nodes.NodeCost;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.jruby.truffle.runtime.objectstorage.ObjectLayout;
import org.jruby.truffle.runtime.objectstorage.ObjectStorage;
import org.jruby.truffle.runtime.objectstorage.ObjectStorageLocation;

@NodeInfo(cost = NodeCost.POLYMORPHIC)
public class WriteObjectObjectFieldNode extends WriteObjectFieldChainNode {

    private final ObjectLayout objectLayout;
    private final ObjectStorageLocation storageLocation;

    public WriteObjectObjectFieldNode(ObjectLayout objectLayout, ObjectStorageLocation storageLocation, WriteObjectFieldNode next) {
        super(next);
        this.objectLayout = objectLayout;
        this.storageLocation = storageLocation;
    }

    @Override
    public void execute(ObjectStorage object, Object value) {
        if (object.getObjectLayout() == objectLayout) {
            storageLocation.write(object, value);
        } else {
            next.execute(object, value);
        }
    }

}
