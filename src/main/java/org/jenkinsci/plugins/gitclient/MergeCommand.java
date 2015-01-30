package org.jenkinsci.plugins.gitclient;

import org.eclipse.jgit.lib.ObjectId;

/**
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 */
public interface MergeCommand extends GitCommand {

    MergeCommand setRevisionToMerge(ObjectId rev);

    MergeCommand setStrategy(Strategy strategy);

    public enum Strategy {
        DEFAULT, RESOLVE, RECURSIVE, OCTOPUS, OURS, SUBTREE;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    /**
     * Select the fast forward mode.
     * The name FastForwardMode collides with org.eclipse.jgit.api.MergeCommand.FastForwardMode
     * so we have to choose a different name.
     */
    MergeCommand setGitPluginFastForwardMode(GitPluginFastForwardMode fastForwardMode);

    public enum GitPluginFastForwardMode {
        FF,        // Default option, fast forward update the branch pointer only
        FF_ONLY,   // Create a merge commit even for a fast forward
        NO_FF;     // Abort unless the merge is a fast forward

        @Override
        public String toString() {
            return "--"+name().toLowerCase().replace("_","-");
        }
    }
}
