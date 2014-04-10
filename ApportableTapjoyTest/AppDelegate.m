//
//  AppDelegate.m
//
//  Created by Ghislain Bernier on 02/04/14.
//  Copyright (c) 2014 All rights reserved.
//

#import "AppDelegate.h"
#import <BridgeKit/AndroidActivity.h>

@implementation AppDelegate

- (void)dealloc
{
    [_window release];
    [super dealloc];
}

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
#ifdef ANDROID
    tapjoyWrapper = [[TapjoyWrapper alloc] initWithActivity:[AndroidActivity currentActivity]];
    
    [tapjoyWrapper setupTapjoy];
#endif
    
    self.window = [[[UIWindow alloc] initWithFrame:[[UIScreen mainScreen] bounds]] autorelease];
    self.window.backgroundColor = [UIColor whiteColor];
    [self.window makeKeyAndVisible];

    return YES;
}

#ifdef ANDROID
- (void)buttonUpWithEvent:(UIEvent *)event
{
    switch (event.buttonCode)
    {
        case UIEventButtonCodeBack:
            //test purpose
            [tapjoyWrapper setupTapjoy];
//            exit(0);
            break;

        case UIEventButtonCodeMenu:
            break;

        default:
            break;
    }
}

- (BOOL)canBecomeFirstResponder
{
    return YES;
}
#endif


@end
